package com.example.checkContent.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
public class Content extends Base {
    private String title;
    @Column(length = 1000)
    private String body;
    private String status;
    private boolean published;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Response> responses;

    public Content(String title, String body) {
        this.title = title;
        this.body = body;
        this.status = "WAITING";
        this.published = false;
    }

    public Content() {}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
