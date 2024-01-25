package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.UserService;
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
    private UserService userService;
    private AuthenticationHelper authenticationHelper;

    @Autowired
    public UserController(UserService userService, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
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

    @GetMapping("/{email}")
    public User getUserByEmail(@RequestHeader HttpHeaders headers, @RequestBody String email) {
        try {
            // ToDo check if this is necessary because every user can search for another user by email
            //  User user = authenticationHelper.tryGetUser(headers);
            //  } catch (AuthorizationException e){
            //  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            return userService.getByEmail(email);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@RequestHeader HttpHeaders headers, @RequestBody String username) {
        try {
            // ToDo check if this is necessary because every user can search for another user by username
            //  User user = authenticationHelper.tryGetUser(headers);
            //  } catch (AuthorizationException e){
            //  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            return userService.getByUsername(username);
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/{firstName}")
    public User getUserByFirstName(@RequestHeader HttpHeaders headers, @RequestBody String firstName) {
        try {
            // ToDo check if this is necessary because every user can search for another user by firstName
            //  User user = authenticationHelper.tryGetUser(headers);
            //  } catch (AuthorizationException e){
            //  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            return userService.getByUsername(firstName);
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    private void checkAccessPermission(int id, User requestingUser) {
        if (!requestingUser.isAdmin() && requestingUser.getId() != id) {
            throw new AuthorizationException(ERROR_MESSAGE);
        }
    }

}