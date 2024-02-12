package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;

    @Autowired
    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers(new UserFilterOptions()));
        return "UsersView";
    }

    @GetMapping("/{id}")
    public String showSingleUser(@PathVariable int id, Model model){
        try {
            model.addAttribute("user", userService.getById(id));
            return "UserView";
        } catch (EntityNotFoundException e){
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @GetMapping("/{id}/userPosts")
    public String showSingleUserPosts(@PathVariable int id, Model model){
        try {
            model.addAttribute("posts", userService.getById(id));
            //TODO double check this part need to be redirected correctly when requested
            return "UserView";
        } catch (EntityNotFoundException e){
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
}
