package com.example.checkContent.resolver;

import com.example.checkContent.dto.AddUserDTO;
import com.example.checkContent.dto.UserDTO;
import com.example.checkContent.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class UserDataFetcher {
    private final UserService userService;

    public UserDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @DgsQuery
    public List<UserDTO> users() {
        return userService.getAllUsersDTO();
    }

    @DgsMutation
    public UserDTO addUser(@InputArgument UserDTO userDTO) {
//        UserDTO user = new UserDTO();
//        user.setUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        user.setRole(userDTO.getRole());
        userService.addUser(userDTO);
        return userDTO;
    }
}
