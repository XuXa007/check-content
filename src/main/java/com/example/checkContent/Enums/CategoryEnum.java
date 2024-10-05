package com.example.checkContent.Enums;

public enum CategoryEnum {
    NEWS("Новости", 0),
    SPORTS("Спорт", 1),
    POLITICS("Политика", 2);

    private String categoryName;
    private int numberOfTypeCategory;

    CategoryEnum(String categoryName, int numberOfTypeCategory) {
        this.categoryName = categoryName;
        this.numberOfTypeCategory = numberOfTypeCategory;
    }


}
