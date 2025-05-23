package com.example.checkContent.model;

import com.example.checkContent.Enums.RoleEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role extends Base {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 50)
    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }

    public Role() {
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}