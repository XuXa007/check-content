package com.example.checkContent.controller;

import com.example.checkContent.dto.UserDTO;
import com.example.checkContent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);

    }

    @GetMapping
    public List<EntityModel<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }
}
