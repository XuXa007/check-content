package com.example.checkContent.dto;

import com.example.checkContent.Enums.CategoryEnum;
import org.springframework.hateoas.RepresentationModel;

public class ContentDTO extends RepresentationModel<ContentDTO> {
    private Long id;
    private String title;
    private String body;
    private String status;
    private boolean published;
    private CategoryEnum categoryEnum;
    private Long userId;

//    private UserDTO user;


    public ContentDTO() {
    }


    public ContentDTO(Long id, String title, String body, String status, boolean published, CategoryEnum categoryEnum, Long userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.status = status;
        this.published = published;
        this.categoryEnum = categoryEnum;
        this.userId = userId;
    }

//    public UserDTO getUser() {
//        return user;
//    }
//
//    public void setUser(UserDTO user) {
//        this.user = user;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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
