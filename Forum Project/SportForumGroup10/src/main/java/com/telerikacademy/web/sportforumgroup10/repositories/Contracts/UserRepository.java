package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getById(int id);

    User getByFirstName(String firstName);

    User getByEmail(String email);

    User getByUsername(String username);

    User create(User user);
    User update(User user);
    User delete(int id);
}
