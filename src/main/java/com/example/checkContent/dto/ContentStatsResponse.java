package com.example.checkContent.dto;

import java.io.Serializable;

public class ContentStatsResponse implements Serializable {
    private Long id;
    private String title;
    private String body;

    public ContentStatsResponse() {
    }

    public ContentStatsResponse(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
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
}
