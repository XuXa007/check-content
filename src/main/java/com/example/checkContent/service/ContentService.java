package com.example.checkContent.service;

import com.example.checkContent.assembler.ContentModelAssembler;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.model.Response;
import com.example.checkContent.repository.ContentRepository;
import com.example.checkContent.repository.ResponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
    private final ContentModelAssembler assembler;

    private final ModelMapper modelMapper;

    @Autowired
    public ContentService(ContentRepository contentRepository, ResponseRepository responseRepository, ContentModelAssembler assembler, ModelMapper modelMapper) {
        this.contentRepository = contentRepository;
        this.responseRepository = responseRepository;
        this.assembler = assembler;
        this.modelMapper = modelMapper;
    }

    public void addContent(ContentDTO contentDTO) {
        contentDTO.setStatus("WAITING");
        contentDTO.setPublished(false);
        Content content = modelMapper.map(contentDTO, Content.class);
        contentRepository.saveAndFlush(content);
    }

    public List<EntityModel<ContentDTO>> getAllContent() {
        return contentRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }


    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

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
            } else {
                content.setStatus("APPROVED");
                contentRepository.save(content);
            }
        }
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
}
