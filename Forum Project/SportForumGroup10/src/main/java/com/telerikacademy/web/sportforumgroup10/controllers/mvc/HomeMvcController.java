package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.*;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.helpers.UserMapper;
import com.telerikacademy.web.sportforumgroup10.models.Dto.ProfileDto;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {

    private final PostService postService;
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;
    private final UserMapper userMapper;

    @Autowired
    public HomeMvcController(PostService postService, UserService userService, AuthenticationHelper authenticationHelper, UserMapper userMapper) {
        this.postService = postService;
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
        this.userMapper = userMapper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {

        return session.getAttribute("currentUser") != null;
    }

    @GetMapping
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/admin")
    public String showAdminPage(HttpSession session, Model model) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            if (user.isAdmin()) {
                return "AdminPanel";
            }
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

    }

    @GetMapping("/about")
    public String showAboutPage(Model model, HttpSession httpSession) {
        try {
            model.addAttribute("loggedIn", authenticationHelper.tryGetCurrentUser(httpSession));
            return "AboutUs";
        } catch (AuthorizationException e) {
            return "AboutUs";
        }
    }

//    @GetMapping("/contact")
//    public String showContactPage(){
//        return "contact";
//    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, HttpSession session) {
        authenticationHelper.tryGetCurrentUser(session);
        model.addAttribute("profile", new ProfileDto());
        return "ProfileView";
    }

    @PostMapping("/profile")
    public String handleProfileUpdate(@Valid @ModelAttribute("profile") ProfileDto profileDto,
                                      BindingResult bindingResult, HttpSession session) {
        User user = authenticationHelper.tryGetCurrentUser(session);
        if (bindingResult.hasErrors()) {
            return "ProfileView";
        }
        try {
            user = userMapper.fromDto(profileDto, user);
            userService.update(user);
            return "redirect:/profile";
        } catch (FirstNameChangeProfileError e) {
            bindingResult.rejectValue("firstName", "profile_error", e.getMessage());
            return "ProfileView";
        } catch (LastNameChangeProfileError e) {
            bindingResult.rejectValue("lastName", "profile_error", e.getMessage());
            return "ProfileView";
        } catch (EmailChangeProfileError e) {
            bindingResult.rejectValue("email", "profile_error", e.getMessage());
            return "ProfileView";
        } catch (PasswordChangeProfileError e) {
            bindingResult.rejectValue("password", "profile_error", e.getMessage());
            return "ProfileView";
        }
    }

//    @GetMapping("/profile")
//    public String handleProfileDelete(@Valid @ModelAttribute("profileDelete") ProfileDto profileDto,
//                                      BindingResult bindingResult, HttpSession session) {
//        User user = authenticationHelper.tryGetCurrentUser(session);
//        userService.delete(user.getId(), user);
//        return "redirect:/";
//    }
}

