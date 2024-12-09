package com.example.checkContent.Enums;

public enum ContentStatus {
    WAITING("На модерации", 0),
    APPROVED("Одобрено", 1),
    REJECTED("Отклонено", 2),
    PUBLISHED("Опубликовано", 3);

    private final String description;
    private final int code;

    ContentStatus(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
