package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers(UserFilterOptions filterOptions);

    User getById(int id);

    User getByFirstName(String firstName);

    User getByEmail(String email);

    User getByUsername(String username);

    User create(User user);

    User update(User user);

    User delete(int id);

    User makeUserAdmin(int id);

    User unmakeUserAdmin(int id);

    User blockUser(int id);

    User unblockUser(int id);

}
