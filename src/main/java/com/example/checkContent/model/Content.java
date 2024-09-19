package com.example.checkContent.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class Content extends RepresentationModel<Content> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String body;
    private String status;
    private boolean published;

    public Content(String title, String body) {
        this.title = title;
        this.body = body;
        this.status = "WAITING";
        this.published = false;
    }

    public Content() {}


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
