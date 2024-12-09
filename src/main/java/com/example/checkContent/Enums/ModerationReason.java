package com.example.checkContent.Enums;

public enum ModerationReason {
    INAPPROPRIATE_CONTENT("Неприемлемый контент"),
    SPAM("Спам"),
    SHORT_CONTENT("Слишком короткий контент"),
    OTHER("Другое");

    private final String description;

    ModerationReason(String description) {
        this.description = description;
    }
}
