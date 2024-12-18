package com.example.checkContent.service;

import com.example.checkContent.Enums.CategoryEnum;
import com.example.checkContent.Enums.ContentStatus;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.dto.ContentModerationForMessage;
import com.example.checkContent.dto.ContentStatsResponse;
import com.example.checkContent.dto.ModerationResult;
import com.example.checkContent.model.Response;
import com.example.checkContent.rabbit.RabbitMQConfiguration;
import com.example.checkContent.rabbit.RabbitMQSender;
import com.example.checkContent.model.Content;
import com.example.checkContent.model.User;
import com.example.checkContent.repository.ContentRepository;
import com.example.checkContent.repository.ResponseRepository;
import com.example.checkContent.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final RabbitMQSender rabbitMQSender;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public ContentService(ContentRepository contentRepository, ResponseRepository responseRepository, UserRepository userRepository, RabbitMQSender rabbitMQSender, ModelMapper modelMapper, RabbitTemplate rabbitTemplate) {
        this.contentRepository = contentRepository;
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.rabbitMQSender = rabbitMQSender;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    public ContentDTO addContent(ContentDTO contentDTO) {
        Content content = modelMapper.map(contentDTO, Content.class);

        content.setStatus(ContentStatus.WAITING);
        content.setCategoryEnum(CategoryEnum.ANOTHER);
        content.setPublished(false);

        if (contentDTO.getUserId() != null) {
            User user = userRepository.findById(contentDTO.getUserId()).orElse(null);
            content.setUser(user);
        }

        content = contentRepository.saveAndFlush(content);
        System.out.println("Контент сохранен в базе данных с ID: " + content.getId());

        System.out.println("Сообщение в notificationService");


        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.CONTENT_MODERATION_QUEUE,
                new ContentModerationForMessage(content.getId(), content.getTitle(), content.getBody())
        );

        System.out.println("Контент с ID " + content.getId() + " отправлен в RabbitMQ");

        System.out.println("Контент с id: " + content.getId() + " вернулся с " + content.getCategoryEnum());
        return modelMapper.map(content, ContentDTO.class);
    }

    public void sendToModeration(ContentDTO contentDTO) {
        ContentModerationForMessage message = new ContentModerationForMessage(
                contentDTO.getId(), contentDTO.getTitle(), contentDTO.getBody()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.CONTENT_MODERATION_QUEUE, message
        );
        System.out.println("Контент отправлен на модерацию: " + message);
    }

    @RabbitListener(queues = RabbitMQConfiguration.CONTENT_STATISTICS_QUEUE)
    public void handleModerationResult(ModerationResult moderationResult) {
        System.out.println("Получен результат модерации для контента с ID: " + moderationResult.getContentId());

        // Получаем контент из базы данных
        Content content = contentRepository.findById(moderationResult.getContentId())
                .orElseThrow(() -> new RuntimeException("Контент не найден"));

        // Обновляем статус контента
        content.setStatus(ContentStatus.valueOf(moderationResult.getStatus()));
        contentRepository.save(content);

        // Сохраняем причину отклонения, если есть
        if ("REJECTED".equals(moderationResult.getStatus())) {
            Response response = responseRepository.findByContent(content);
            if (response == null) {
                response = new Response();
                response.setContent(content);
            }
            response.setMessage(moderationResult.getMes());
            responseRepository.save(response);
        }

        // Уведомляем пользователя через WebSocket
        String notification = String.format("Контент с ID %d %s.",
                content.getId(),
                moderationResult.getStatus().equals("APPROVED") ? "одобрен" : "отклонен"
        );
        rabbitTemplate.convertAndSend("firstQueue", notification);

        System.out.println("Контент с ID " + content.getId() + " обновлен. Уведомление отправлено.");
    }

    @RabbitListener(queues = RabbitMQConfiguration.CONTENT_STATISTICS_QUEUE)
    public void handleAnalysisResult(ContentStatsResponse response) {
        System.out.println("Получен результат анализа: " + response);
    }


    private CategoryEnum mapGrpcCategory(com.example.contentanalysis.grpc.Category grpcCategory) {
        return switch (grpcCategory) {
            case NEWS -> CategoryEnum.NEWS;
            case EDUCATION -> CategoryEnum.EDUCATION;
            case TECH -> CategoryEnum.TECH;
            case SPORT -> CategoryEnum.SPORTS;
            case ANOTHER -> CategoryEnum.ANOTHER;
            case POLITICS -> CategoryEnum.POLITICS;
            case UNRECOGNIZED -> null;
        };
    }

    private void sendToModeration(Content content) {
        try {
            ContentModerationForMessage message = ContentModerationForMessage.fromContent(content);
            rabbitMQSender.sendToModeration(message);
        } catch (Exception e) {
            System.out.println("Ошибка модерции контента с id " + content.getId());
        }
    }

    public ContentDTO approveContent(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Контент не нашелся"));
        sendToModeration(content);
        return modelMapper.map(content, ContentDTO.class);
    }

//    public void rejectContent(Content content, String reason) {
//        content.setStatus(ContentStatus.REJECTED);
//        contentRepository.save(content);
//
//        Response response=responseRepository.findByContent(content);
//        if (response==null) {
//            response=new Response();
//            response.setContent(content);
//        }
//        response.setMessage(reason);
//        responseRepository.save(response);
//    }

    public ContentDTO publishContent(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(RuntimeException::new);
        System.out.println("Контент c Id " + content.getId() + " направлен на публикцию с " + content.isPublished());
        if (content.getStatus() == ContentStatus.APPROVED) {
            content.setPublished(true);
            content.setStatus(ContentStatus.PUBLISHED);
            content = contentRepository.save(content);
            rabbitMQSender.publishedContent(content);
            return modelMapper.map(content, ContentDTO.class);
        }
        throw new RuntimeException("Контент не опубликован");
    }

    public List<ContentDTO> getAllContent() {
        return contentRepository.findAll().stream()
                .map(content -> modelMapper.map(content, ContentDTO.class))
                .collect(Collectors.toList());
    }


    public ContentDTO getContentById(Long id) {
        return contentRepository.findById(id)
                .map(content -> modelMapper.map(content, ContentDTO.class))
                .orElseThrow(RuntimeException::new);
    }


    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    public List<ContentDTO> getPublishedContent() {
//        return contentRepository.findAllByPublishedTrue();
//    }

    public boolean updateContent(Long id, String newTit, String newBody) {
        Optional<Content> optionalContent = contentRepository.findById(id);
        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();
            content.setTitle(newTit);
            content.setBody(newBody);
            contentRepository.save(content);
            return true;
        }
        return false;
    }

    public List<ContentDTO> getAllContentDTO() {
        return contentRepository.findAll().stream()
                .map(content -> modelMapper.map(content, ContentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ContentDTO> getContentByIdDTO(Long id) {
        return contentRepository.findById(id).map(content -> modelMapper.map(content, ContentDTO.class));
    }

}
