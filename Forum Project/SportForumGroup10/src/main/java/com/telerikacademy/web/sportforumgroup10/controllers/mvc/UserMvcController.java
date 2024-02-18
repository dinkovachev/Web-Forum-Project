package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.models.Dto.UserFilterDto;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;

    private final PostService postService;

    private final AuthenticationHelper authenticationHelper;


    @Autowired
    public UserMvcController(UserService userService, PostService postService, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
    }
    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session){
        return session.getAttribute("currentUser") != null;
    }
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }
    //TODO double check how to implement ModelAttribute is blocked

    @GetMapping
    public String showAllUsers(@ModelAttribute("userFilterOptions") UserFilterDto userFilterDto, Model model) {
        UserFilterOptions userFilterOptions = new UserFilterOptions(
                userFilterDto.getFirstName(),
                userFilterDto.getUsername(), userFilterDto.getEmail(),
                userFilterDto.getSortBy(), userFilterDto.getOrderBy());
        model.addAttribute("users", userService.getAllUsers(userFilterOptions));
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
    @GetMapping("/userComments:{id}")
    public String showSingleUserComments(@PathVariable int id, Model model){
        //TODO double check this part need to be redirected correctly when requested
        try {
            User user = userService.getById(id);
            model.addAttribute("user", user);

            model.addAttribute("comments",user.getUsersComments());
            return "UserCommentsView";


        } catch (EntityNotFoundException e){
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/block:{id}")
    public String handleUserBlock(@PathVariable int id, @ModelAttribute("block") User user, Model model,
                                  HttpSession session) {
        User userModifier = authenticationHelper.tryGetCurrentUser(session);
        try {
            userService.blockUser(id, userModifier);
            return "redirect:/users";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        }
    }

    @PostMapping("/unblock:{id}")
    public String handleUserUnblock(@PathVariable int id, @ModelAttribute("unblock") User user, Model model,
                                  HttpSession session) {
        User userModifier = authenticationHelper.tryGetCurrentUser(session);
        try {
            userService.unblockUser(id, userModifier);
            return "redirect:/users";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        }
    }

    @PostMapping("/makeAdmin:{id}")
    public String handleUserMakeAdmin(@PathVariable int id, @ModelAttribute("makeAdmin") User user, Model model,
                                    HttpSession session) {
        User userModifier = authenticationHelper.tryGetCurrentUser(session);
        try {
            userService.makeUserAdmin(id, userModifier);
            return "redirect:/users";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        }
    }

    @PostMapping("/unmakeAdmin:{id}")
    public String handleUserUnMakeAdmin(@PathVariable int id, @ModelAttribute("unmakeAdmin") User user, Model model,
                                      HttpSession session) {
        User userModifier = authenticationHelper.tryGetCurrentUser(session);
        try {
            userService.unmakeUserAdmin(id, userModifier);
            return "redirect:/users";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        }
    }

    @PostMapping("/delete:{id}")
    public String handleUserDelete(@PathVariable int id, @ModelAttribute("delete") User user, Model model,
                                      HttpSession session) {
        User userModifier = authenticationHelper.tryGetCurrentUser(session);
        try {
            userService.delete(id, userModifier);
            return "redirect:/users";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        }
    }


}
