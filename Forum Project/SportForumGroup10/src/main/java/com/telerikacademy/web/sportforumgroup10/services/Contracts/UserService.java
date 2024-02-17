package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;

import java.util.List;

public interface UserService {
    List<User> getAllUsers(UserFilterOptions filterOptions);
    User getById(int id);
    User getByFirstName(String firstName, User user);
    User getByEmail(String email, User user);
    User getByUsername(String username, User user);
    User getByUsernameAuthentication(String username);
    User create(User user);
    User update(User user);
    User delete(int id, User userModifier);
    User makeUserAdmin(int id, User userModifier);
    User unmakeUserAdmin(int id, User userModifier);
    User blockUser(int id, User userModifier);
    User unblockUser(int id, User userModifier);

    long getUserCount();
}
