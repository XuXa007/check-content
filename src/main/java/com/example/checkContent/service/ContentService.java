package com.example.checkContent.service;

import com.example.checkContent.model.Content;
import com.example.checkContent.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private static final int MIN_TITLE_LENGTH = 10;
    private static final int MIN_BODY_LENGTH = 50;

    @Autowired
    private ContentRepository contentRepository;

    public Content addContent(String title, String body) {
        Content content = new Content();
        content.setTitle(title);
        content.setBody(body);
        content.setStatus("WAITING");
        content.setPublished(false);
        contentRepository.save(content);
        return contentRepository.save(content);
    }

//    public CollectionModel<EntityModel<Content>> getAllContent() {
//        return contentRepository.findAll();
//    }

    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    public String approveContent(Long id) {
        Optional<Content> contents=contentRepository.findById(id);
        if (contents.isPresent()) {
            Content content=contents.get();

            if (content.getTitle().length()<MIN_TITLE_LENGTH) {
                content.setStatus("REJECTED");
                contentRepository.save(content);
                return "rejected: title must be at least " + MIN_TITLE_LENGTH ;
            }

            if (content.getBody().length()<MIN_BODY_LENGTH) {
                content.setStatus("REJECTED");
                contentRepository.save(content);
                return "rejected: body lenght must be at least " + MIN_BODY_LENGTH ;
            }
            content.setStatus("APPROVED");
            contentRepository.save(content);
            return "approved";
        }
        return "not found";
    }

    public boolean publishContent(Long id) {
        Optional<Content> contents = contentRepository.findById(id);
        if (contents.isPresent()) {
            Content content = contents.get();
            if ("APPROVED".equals(content.getStatus())) {
                content.setPublished(true);
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

    public List<Content> getPublishedContent() {
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

}
