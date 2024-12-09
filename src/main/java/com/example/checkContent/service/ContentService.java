package com.example.checkContent.service;

import com.example.checkContent.Enums.CategoryEnum;
import com.example.checkContent.assembler.ContentModelAssembler;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.dto.ContentModerationForMessage;
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
    private static final int MIN_TITLE_LENGTH = 10;
    private static final int MIN_BODY_LENGTH = 50;

    private final ContentRepository contentRepository;
    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final ContentModelAssembler assembler;

    private final RabbitMQSender rabbitMQSender;
    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper modelMapper;

    static final String exchangeName="contentToModerationQueue";

    @Autowired
    public ContentService(ContentRepository contentRepository, ResponseRepository responseRepository,
                          UserRepository userRepository, ContentModelAssembler assembler,
                          RabbitMQSender rabbitMQSender, RabbitTemplate rabbitTemplate, ModelMapper modelMapper) {
        this.contentRepository = contentRepository;
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.assembler = assembler;
        this.rabbitMQSender = rabbitMQSender;
        this.rabbitTemplate = rabbitTemplate;
        this.modelMapper = modelMapper;
    }

    public ContentDTO addContent(ContentDTO contentDTO) {
        Content content=modelMapper.map(contentDTO, Content.class);

        content.setCategoryEnum(CategoryEnum.NEWS);
        content.setStatus("WAITING");
        content.setPublished(false);

        if (contentDTO.getUserId() != null) {
            User user = userRepository.findById(contentDTO.getUserId()).orElse(null);
            content.setUser(user);
        }

        content = contentRepository.saveAndFlush(content);
        sendToModeration(content);
        return modelMapper.map(content, ContentDTO.class);
    }

    private void sendToModeration(Content content) {
        try {
            System.out.println("Контент отправлен на модерцию с id " + content.getId());

            ContentModerationForMessage message = ContentModerationForMessage.fromContent(content);
            rabbitMQSender.sendToModeration(message);
        } catch (Exception e) {
            System.out.println("Ошибка модерции контента с id " + content.getId());

        }
    }

    public List<ContentDTO> getAllContent() {
        return contentRepository.findAll().stream()
                .map(content -> modelMapper.map(content, ContentDTO.class))
                .collect(Collectors.toList());
    }


    public ContentDTO getContentById(Long id) {
        Content content=contentRepository.findById(id).orElse(null);
        return modelMapper.map(content, ContentDTO.class);
    }

//    @RabbitListener(queues = RabbitMQConfiguration.queueContentName)
    public void approveContent(Long id) {
        Optional<Content> optionalContent = contentRepository.findById(id);
        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();
            if (content.getTitle().length() < MIN_TITLE_LENGTH) {
                content.setStatus("REJECTED");
                contentRepository.save(content);

                Response response = responseRepository.findByContent(content);
                if (response == null) {
                    response = new Response();
                    response.setContent(content);
                }
                response.setMessage("rejected: title must be at least " + MIN_TITLE_LENGTH);
                responseRepository.save(response);
//                System.out.println("Контент с ID "+id+ " не одобрен из-за длинны заголовка");

            } else if (content.getBody().length() < MIN_BODY_LENGTH) {
                content.setStatus("REJECTED");
                contentRepository.save(content);

                Response response = responseRepository.findByContent(content);
                if (response == null) {
                    response = new Response();
                    response.setContent(content);
                }
                response.setMessage("rejected: body length must be at least " + MIN_BODY_LENGTH);
                responseRepository.save(response);

//                System.out.println("Контент с ID "+id+ " не одобрен из-за длинны тела");
            } else {
                content.setStatus("APPROVED");
                contentRepository.save(content);
//                System.out.println("Контент с ID "+id+ " был одобрен");
            }
        } else {
//            System.out.println("Контент с ID "+id+" не найден");
        }


//        if (optionalContent.isPresent()) {
//            Content content = optionalContent.get();
//
//            if (content.getTitle().length() < MIN_TITLE_LENGTH) {
//                content.setStatus("REJECTED");
//                contentRepository.save(content);
//
//                Response response = responseRepository.findByContent(content);
//                if (response == null) {
//                    response = new Response();
//                    response.setContent(content);
//                }
//                response.setMessage("rejected: title must be at least " + MIN_TITLE_LENGTH);
//                responseRepository.save(response);
//            } else if (content.getBody().length() < MIN_BODY_LENGTH) {
//                content.setStatus("REJECTED");
//                contentRepository.save(content);
//
//                Response response = responseRepository.findByContent(content);
//                if (response == null) {
//                    response = new Response();
//                    response.setContent(content);
//                }
//                response.setMessage("rejected: body length must be at least " + MIN_BODY_LENGTH);
//                responseRepository.save(response);
//            } else {
//                content.setStatus("APPROVED");
//                contentRepository.save(content);
//            }
//        }

    }

//    @RabbitListener(queues = RabbitMQConfiguration.queueContentName)
    public boolean publishContent(Long id) {
//        System.out.println("Контент : " + id+" на публикации");
        Optional<Content> contents = contentRepository.findById(id);
        if (contents.isPresent()) {
            Content content = contents.get();
            if ("APPROVED".equals(content.getStatus())) {
                content.setPublished(true);
                content.setStatus("PUBLISHED");
                contentRepository.save(content);

                Content content1 = contentRepository.findContentById(content.getId());

                rabbitMQSender.publishedContent(content1);

//                System.out.println("Опубликован контент: " + id);

                return true;
            }
        }
        return false;
    }

    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ContentDTO> getPublishedContent() {
        return contentRepository.findAllByPublishedTrue();
    }

    public boolean updateContent(Long id, String newTit, String newBody) {
        Optional<Content> optionalContent=contentRepository.findById(id);
        if (optionalContent.isPresent()) {
            Content content=optionalContent.get();
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
