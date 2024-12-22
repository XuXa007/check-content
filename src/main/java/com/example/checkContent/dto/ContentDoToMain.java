package com.example.checkContent.dto;

import com.example.checkContent.Enums.CategoryEnum;
import com.example.checkContent.Enums.ContentStatus;

import java.io.Serializable;

public class ContentDoToMain implements Serializable {
    private Long id;
    private ContentStatus contentStatus;
    private String message;
    private CategoryEnum categoryEnum;

    public ContentDoToMain() {
    }

    public ContentDoToMain(Long id, ContentStatus contentStatus, String message, CategoryEnum categoryEnum) {
        this.id = id;
        this.contentStatus = contentStatus;
        this.message = message;
        this.categoryEnum = categoryEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
