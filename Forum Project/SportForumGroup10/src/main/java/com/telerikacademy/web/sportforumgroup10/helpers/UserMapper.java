package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.Dto.ProfileDto;
import com.telerikacademy.web.sportforumgroup10.models.Dto.RegisterDto;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.Dto.UserDTO;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;

    private final AuthenticationHelper authenticationHelper;

    public UserMapper(UserService userService, AuthenticationHelper authenticationHelper) {

        this.userService = userService;

        this.authenticationHelper = authenticationHelper;
    }

    public User fromDto(int id, UserDTO userDTO) {
        User user = fromDto(userDTO);
        user.setId(id);
        User repositoryUser = userService.getById(id);
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

    public User fromDto(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        return user;
    }

    public User fromDto(ProfileDto profileDto, User user) {
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setEmail(profileDto.getEmail());
        user.setPassword(profileDto.getPassword());

//        TODO double check how to do the phone number field
//        user.setPhoneNumber(profileDto.getPhoneNumber());
        return user;
    }

}
