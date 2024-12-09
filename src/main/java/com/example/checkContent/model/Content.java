package com.example.checkContent.model;

import com.example.checkContent.Enums.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "contents")
public class Content extends Base implements Serializable {
    private String title;
    @Column(length = 1000)
    private String body;
    private String status;
    private boolean published;
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryEnum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Response> responses;

    public Content(String title, String body) {
        this.title = title;
        this.body = body;
        this.status = "WAITING";
        this.published = false;
    }

    public Content() {}

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
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

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", status='" + status + '\'' +
                ", published=" + published +
                ", categoryEnum=" + categoryEnum +
                ", user=" + user +
                ", responses=" + responses +
                '}';
    }
}
