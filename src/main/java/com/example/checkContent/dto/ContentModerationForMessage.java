package com.example.checkContent.dto;

import com.example.checkContent.Enums.CategoryEnum;
import com.example.checkContent.model.Content;

import java.io.Serializable;


public class ContentModerationForMessage implements Serializable {
    private Long id;
    private String title;
    private String body;
    private CategoryEnum categoryEnum;
    private Long userId;
//    private LocalDateTime createdAt;


    public ContentModerationForMessage(Long id, String title, String body, CategoryEnum categoryEnum, Long userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.categoryEnum = categoryEnum;
        this.userId = userId;
    }

    // Фабричный метод
    public static ContentModerationForMessage fromContent(Content content) {
        return new ContentModerationForMessage(
                content.getId(),
                content.getTitle(),
                content.getBody(),
                content.getCategoryEnum(),
                content.getUser() != null ? content.getUser().getId() : null
        );
    }

    public ContentModerationForMessage() {
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

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
