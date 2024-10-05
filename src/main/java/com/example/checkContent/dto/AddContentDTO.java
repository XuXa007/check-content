package com.example.checkContent.dto;


public class AddContentDTO {
    public String title;
    public String body;
//    @Enumerated(EnumType.STRING)
//    private CategoryEnum categoryEnum;

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
