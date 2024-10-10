package com.example.checkContent.service;

import com.example.checkContent.Enums.CategoryEnum;
import com.example.checkContent.assembler.ContentModelAssembler;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.dto.UserDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.model.User;
import com.example.checkContent.repository.ContentRepository;
import com.example.checkContent.repository.ResponseRepository;
import com.example.checkContent.repository.UserRepository;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    @Autowired
    public ContentService(ContentRepository contentRepository, ResponseRepository responseRepository, UserRepository userRepository, ContentModelAssembler assembler, ModelMapper modelMapper) {
        this.contentRepository = contentRepository;
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.assembler = assembler;
        this.modelMapper = modelMapper;
    }

    public ContentDTO addContent(ContentDTO contentDTO) {
        Content content=modelMapper.map(contentDTO, Content.class);
        User user=userRepository.findById(contentDTO.getUserId()).orElse(null);
        content.setUser(user);

//        content.setTitle(contentDTO.getTitle());
//        content.setBody(contentDTO.getBody());
        content.setCategoryEnum(CategoryEnum.NEWS);
        content.setStatus("WAITING");
        content.setPublished(false);


        Content contentForSaveId = contentRepository.saveAndFlush(content);

        ContentDTO contentToDTOForUser = modelMapper.map(contentForSaveId, ContentDTO.class);
        contentToDTOForUser.setUser(modelMapper.map(user, UserDTO.class));
        return contentToDTOForUser;
//        contentDTO.setStatus("WAITING");
//        contentDTO.setPublished(false);
//        Content content = modelMapper.map(contentDTO, Content.class);
//        contentRepository.saveAndFlush(content);
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

    public void approveContent(Long id) {
        Content content=contentRepository.findById(id).orElse(null);
        assert content != null;
        content.setStatus("APPROVE");
        contentRepository.save(content);
//        Optional<Content> optionalContent = contentRepository.findById(id);
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


    public boolean publishContent(Long id) {
        Optional<Content> contents = contentRepository.findById(id);
        if (contents.isPresent()) {
            Content content = contents.get();
            if ("APPROVED".equals(content.getStatus())) {
                content.setPublished(true);
                content.setStatus("PUBLISHED");
                contentRepository.save(content);
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
