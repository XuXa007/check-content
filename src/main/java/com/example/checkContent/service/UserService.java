package com.example.checkContent.service;

import com.example.checkContent.model.User;
import com.example.checkContent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
