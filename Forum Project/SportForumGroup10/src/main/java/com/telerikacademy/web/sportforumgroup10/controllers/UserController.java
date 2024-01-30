package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityDuplicateException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.helpers.PostMapper;
import com.telerikacademy.web.sportforumgroup10.helpers.UserMapper;
import com.telerikacademy.web.sportforumgroup10.models.Dto.UserDTO;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final String ERROR_MESSAGE = "You are not authorized to receive this information";
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, AuthenticationHelper authenticationHelper, UserMapper userMapper) {
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            // TODO maybe this logic can be in the service layer
            if (!user.isAdmin()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_MESSAGE);
            }
            return userService.getAllUsers();
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkAccessPermission(id, user);
            return userService.getById(id);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/email:{email}")
    public User getUserByEmail(@RequestHeader HttpHeaders headers, @PathVariable String email) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkAccessPermission(user.getId(), user);
            return userService.getByEmail(email);
            //TODO check if need to catch authorization exception
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/username:{username}")
    public User getUserByUsername(@RequestHeader HttpHeaders headers, @PathVariable String username) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkAccessPermission(user.getId(), user);
            return userService.getByUsername(username);
            //TODO check if need to catch authorization exception
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/firstName:{firstName}")
    public User getUserByFirstName(@RequestHeader HttpHeaders headers, @PathVariable String firstName) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkAccessPermission(user.getId(), user);
            return userService.getByFirstName(firstName);
            //TODO check if need to catch authorization exception
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public User create(@Valid @RequestBody UserDTO userDTO) {
        try {
            User user = userMapper.fromDto(userDTO);
            return userService.create(user);
        } catch (EntityDuplicateException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
//    @PutMapping("/{id}")
//    public User update(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody UserDTO userDTO){
//        try {
//            User user = authenticationHelper.tryGetUser(headers);
//            //TODO double check how to change the information
//        }
//    }

    private void checkAccessPermission(int id, User requestingUser) {
        if (!requestingUser.isAdmin() && requestingUser.getId() != id) {
            throw new AuthorizationException(ERROR_MESSAGE);
        }
    }

}
