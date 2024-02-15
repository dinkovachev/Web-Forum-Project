package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;

    private final PostService postService;


    @Autowired
    public UserMvcController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }
    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session){
        return session.getAttribute("currentUser") != null;
    }
    //TODO double check how to implement ModelAttribute is blocked
//    @ModelAttribute("isBlocked")
//    public boolean populateIsBlocked(HttpSession session){
//        return session.getAttribute("currentUser") != false;
//    }

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
    //TODO need to check with the colleagues if we can do it from their implementation currently stays on UserView page
    @GetMapping("/userPosts:{id}")
    public String showSingleUserPosts(@PathVariable int id, Model model){
        //TODO double check this part need to be redirected correctly when requested
        try {
            User user = userService.getById(id);
            model.addAttribute("user", user);
            model.addAttribute("posts", user.getUsersPosts());
            return "UserPostsView";


        } catch (EntityNotFoundException e){
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }


}
