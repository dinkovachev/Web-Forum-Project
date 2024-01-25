package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getById(int id);

    User getByFirstName(String firstName);

    User getByEmail(String email);

    User getByUsername(String username);

    User create(User user);
}
