package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(int id, User user);
    User getByFirstName(String firstName, User user);
    User getByEmail(String email, User user);
    User getByUsername(String username, User user);
    User getByUsernameAuthentication(String username);
    User create(User user);
    User update(User user);
    User delete(int id, User userModifier);
}
