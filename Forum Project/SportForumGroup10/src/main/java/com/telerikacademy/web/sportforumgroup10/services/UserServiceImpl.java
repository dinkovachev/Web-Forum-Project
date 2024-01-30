package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityDuplicateException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.UserRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByFirstName(String firstName) {
        return userRepository.getByFirstName(firstName);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User create(User user) {
        boolean duplicateUserExists = true;
        try {
            userRepository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e){
            duplicateUserExists = false;
        }
        if (duplicateUserExists){
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }
            return userRepository.create(user);
    }

    @Override
    public User update(User user) {
        User userToUpdate = getById(user.getId());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.update(userToUpdate);
    }

    @Override
    public User delete(int id) {
        return userRepository.delete(id);
    }
}
