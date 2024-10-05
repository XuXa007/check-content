package com.example.checkContent.model;

import com.example.checkContent.Enums.RoleEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends Base {
    private String username;
    private String email;
    private RoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Content> contents;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Response> responses;


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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    //    public List<Response> getResponses() {
//        return responses;
//    }
//
//    public void setResponses(List<Response> responses) {
//        this.responses = responses;
//    }
}
