package com.example.checkContent.service;

import com.example.checkContent.Enums.RoleEnum;
import com.example.checkContent.assembler.UserModelAssembler;
import com.example.checkContent.dto.UserDTO;
import com.example.checkContent.model.User;
import com.example.checkContent.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final UserModelAssembler assembler;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserModelAssembler assembler) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.assembler = assembler;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(RoleEnum.Admin);
        userRepository.saveAndFlush(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public List<EntityModel<UserDTO>> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

    }

    public List<UserDTO> getAllUsersDTO() {
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
    }
}
