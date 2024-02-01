package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityDuplicateException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.UserRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String ERROR_MESSAGE = "You are not authorized";
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(UserFilterOptions filterOptions) {

        return userRepository.getAllUsers(filterOptions);
    }

    //TODO maybe no need only admins to search by id, firstName, email, username
    // as it is mentioned in the requirements admin section
    @Override
    public User getById(int id, User user) {
        checkAccessPermissionId(id, user);
        return userRepository.getById(id);
    }

    @Override
    public User getByFirstName(String firstName, User user) {

        checkAccessPermissionString(firstName, user);
        return userRepository.getByFirstName(firstName);
    }

    @Override
    public User getByEmail(String email, User user) {
        checkAccessPermissionString(email, user);
        return userRepository.getByEmail(email);
    }

    @Override
    public User getByUsername(String username, User user) {
        checkAccessPermissionString(username, user);
        return userRepository.getByUsername(username);
    }

    @Override
    public User getByUsernameAuthentication(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User create(User user) {
        boolean duplicateUserExists = true;
        try {
            userRepository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateUserExists = false;
        }
        if (duplicateUserExists) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }
        return userRepository.create(user);
    }

    @Override
    public User update(User user) {
        checkAccessPermissionId(user.getId(), user);
        User userToUpdate = getById(user.getId(), user);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.update(userToUpdate);
    }

    @Override
    public User delete(int id, User userModifier) {
        checkAccessPermissionId(id, userModifier);
        return userRepository.delete(id);
    }

    private void checkAccessPermissionId(int id, User requestingUser) {
        if (!requestingUser.isAdmin()) {
            if (requestingUser.getId() != id) {
                throw new AuthorizationException(ERROR_MESSAGE);
            }
        }
    }
    private void checkAccessPermissionString(String input, User requestingUser) {
        if (!requestingUser.isAdmin()) {
//            if (!requestingUser.getUsername().equals(input)) {
                throw new AuthorizationException(ERROR_MESSAGE);
            }

    }
}
