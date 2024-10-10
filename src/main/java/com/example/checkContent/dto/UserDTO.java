package com.example.checkContent.dto;

import com.example.checkContent.Enums.RoleEnum;
import org.springframework.hateoas.RepresentationModel;

public class UserDTO extends RepresentationModel<UserDTO> {
    private Long id;
    private String username;
    private String email;
    private RoleEnum role;

    public UserDTO(Long id, String username, String email, RoleEnum role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
