package com.example.checkContent.Enums;

public enum CategoryEnum {
    NEWS("Новости", 0),
    SPORTS("Спорт", 1),
    POLITICS("Политика", 2),
    ANOTHER("Другое", 3),
    EDUCATION("Образование", 4),
    TECH("Техника", 5);


    private String categoryName;
    private int numberOfTypeCategory;

    CategoryEnum(String categoryName, int numberOfTypeCategory) {
        this.categoryName = categoryName;
        this.numberOfTypeCategory = numberOfTypeCategory;
    }
}
