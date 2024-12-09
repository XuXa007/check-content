package com.example.checkContent.service;

import com.example.checkContent.Enums.RoleEnum;
import com.example.checkContent.assembler.UserModelAssembler;
import com.example.checkContent.dto.UserDTO;
import com.example.checkContent.model.User;
import com.example.checkContent.rabbit.RabbitMQSender;
import com.example.checkContent.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserModelAssembler assembler;
    private final RabbitMQSender rabbitMQSender;
    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       UserModelAssembler assembler, RabbitMQSender rabbitMQSender) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.assembler = assembler;
        this.rabbitMQSender = rabbitMQSender;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(RoleEnum.Admin);
        userRepository.saveAndFlush(user);
        modelMapper.map(user, UserDTO.class);

//        rabbitMQSender.userCreated(userDTO);
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

    public List<UserDTO> getSortedUsers(String sortBy) {
        Sort sort;
        if ("USERNAME_DESC".equals(sortBy)) {
            sort = Sort.by(Sort.Direction.DESC, "username");
        } else {
            sort = Sort.by(Sort.Direction.ASC, "username");
        }

        List<User> users = userRepository.findAll(sort);

        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
