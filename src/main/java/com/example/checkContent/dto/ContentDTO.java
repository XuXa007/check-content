package com.example.checkContent.dto;

import com.example.checkContent.model.Content;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class ContentDTO extends RepresentationModel<ContentDTO> {
    private Long id;
    private String title;
    private String body;
    private String status;
    private boolean published;

    public ContentDTO(Long id, String title, String body, String status, boolean published) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.status = status;
        this.published = published;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
