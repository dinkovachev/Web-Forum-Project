package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {

    private final PostService postService;
    private  final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    public HomeMvcController(PostService postService, UserService userService, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session){

        return session.getAttribute("currentUser") != null;
    }

    @GetMapping
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/admin")
    public String showAdminPage(){
        return "AdminPanel";
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
    public String showProfilePage(){
        return "profile";
    }


}
