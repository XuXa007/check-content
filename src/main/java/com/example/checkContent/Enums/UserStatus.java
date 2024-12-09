package com.example.checkContent.Enums;

public enum UserStatus {
    ACTIVE("Активный",0),
    WARNING("Подозрительный",1),
    BLOCKED("Заблокированный", 2);

    private String userStatus;
    private int num;
    UserStatus(String userStatus, int num) {
        this.userStatus = userStatus;
        this.num=num;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return userStatus;
    }
}
