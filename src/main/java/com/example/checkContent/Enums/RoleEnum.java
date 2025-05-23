package com.example.checkContent.Enums;

public enum RoleEnum {
    User("Пользователь", 0),
    Admin("Администратор", 1),
    Moderator("Модератор", 2);

    private String type;
    private int number;

    RoleEnum(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
