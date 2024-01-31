package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.Dto.UserDTO;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {

        this.userService = userService;
    }

    public User fromDto(int id, UserDTO userDTO) {
        User user = fromDto(userDTO);
        user.setId(id);
        User repositoryUser = userService.getById(id, user);
        return user;
    }

    public User fromDto(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
