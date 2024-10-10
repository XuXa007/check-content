package com.example.checkContent.dto;


public class AddContentDTO {
    public String title;
    public String body;
    private Long userId;
//    @Enumerated(EnumType.STRING)
//    private CategoryEnum categoryEnum;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

//    public CategoryEnum getCategoryEnum() {
//        return categoryEnum;
//    }
//
//    public void setCategoryEnum(CategoryEnum categoryEnum) {
//        this.categoryEnum = categoryEnum;
//    }
}
