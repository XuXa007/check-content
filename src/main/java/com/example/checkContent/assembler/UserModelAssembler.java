package com.example.checkContent.assembler;

import com.example.checkContent.controller.UserController;
import com.example.checkContent.dto.UserDTO;
import com.example.checkContent.model.User;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<UserDTO>> {
    private final ModelMapper mapper;

    public UserModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public @NotNull EntityModel<UserDTO> toModel(@NotNull User user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);

        EntityModel<UserDTO> userDTOEntityModel = EntityModel.of(userDTO);

        if (user.getRole() != null) {
            switch (user.getRole()) {
                case User -> userDTOEntityModel.add(linkTo(methodOn(UserController.class).getAllUsers())
                        .withRel("show all")
                        .withType("GET")
                        .withDeprecation("посмотреть всех пользователей"));

                case Admin -> userDTOEntityModel.add(linkTo(methodOn(UserController.class).getAllUsers())
                        .withRel("show all")
                        .withType("GET")
                        .withDeprecation("посмотреть всех пользователей"));

                case Moderator -> userDTOEntityModel.add(linkTo(methodOn(UserController.class).getAllUsers())
                        .withRel("show all")
                        .withType("GET")
                        .withDeprecation("посмотреть всех пользователей"));
            }
        } else {
            // Обработка случая, когда роль не указана (например, добавляем ссылку по умолчанию или ничего не делаем)
            userDTOEntityModel.add(linkTo(methodOn(UserController.class).getAllUsers())
                    .withRel("show all")
                    .withType("GET"));
        }
        return userDTOEntityModel;

    }
}
