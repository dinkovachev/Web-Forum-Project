package com.telerikacademy.web.sportforumgroup10.controllers.mvc;


import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.LikeService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts/{postId}/like")
public class LikeMvcController {

    private final LikeService likeService;

    private final AuthenticationHelper authenticationHelper;


    @Autowired
    public LikeMvcController(LikeService likeService, AuthenticationHelper authenticationHelper) {
        this.likeService = likeService;
        this.authenticationHelper = authenticationHelper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("requestURI")
    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @PostMapping
    public String addLike(@PathVariable int postId, HttpSession httpSession, Model model) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
            likeService.createLike(postId, user);
            return "redirect:/posts/{postId}";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", "Not authorized");
            return "ErrorView";
        }
    }

    @PostMapping("/delete")
    public String removeLike(@PathVariable int postId, HttpSession httpSession, Model model) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
            likeService.removeLike(postId, user);
            return "redirect:/posts/{postId}";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
}
