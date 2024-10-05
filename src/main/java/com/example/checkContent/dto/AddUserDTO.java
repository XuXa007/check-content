package com.example.checkContent.dto;

import com.example.checkContent.Enums.RoleEnum;

public class AddUserDTO {
    private String username;
    private String email;
    private RoleEnum role;

    public AddUserDTO() {
    }

    public AddUserDTO(String username, String email, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
