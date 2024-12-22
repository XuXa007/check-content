package com.example.checkContent.dto;

import com.example.checkContent.Enums.ContentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentAfterModeration implements Serializable {
    private Long id;
    private String title;
    private String body;
    private ContentStatus contentStatus;
    private String message;

    public ContentAfterModeration() {
    }

    public ContentAfterModeration(Long id, String title, String body, ContentStatus contentStatus, String message) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.contentStatus = contentStatus;
        this.message = message;
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

    public ContentStatus getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
