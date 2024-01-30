package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(int id);
    User getByFirstName(String firstName);
    User getByEmail(String email);
    User getByUsername(String username);
    User create(User user);
}
