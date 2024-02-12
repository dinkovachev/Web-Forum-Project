package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/likes")
public class LikeController {

    public static final String PERMISSION_ERROR = "You don't have permission.";


    private final LikeService likeService;
    private final AuthenticationHelper authenticationHelper;


    @Autowired
    public LikeController(LikeService likeService, AuthenticationHelper authenticationHelper) {
        this.likeService = likeService;
        this.authenticationHelper = authenticationHelper;
    }

    @PostMapping("/{postId}")
    public Like addLike(@PathVariable int postId, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            return likeService.createLike(postId, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{likeId}")
    public Like removeLike(@PathVariable int likeId, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            return likeService.removeLike(likeId, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public List<Like> getAllPostLikes(@PathVariable int postId) {
        try {
            return likeService.allPostLikes(postId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/countLikes:{postId}")
    public int countLikes(@PathVariable int postId) {
        try {
            return likeService.countLikes(postId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    private void checkIsUserBlocked(User user) {
        if (user.isBlocked()) {
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }
}


