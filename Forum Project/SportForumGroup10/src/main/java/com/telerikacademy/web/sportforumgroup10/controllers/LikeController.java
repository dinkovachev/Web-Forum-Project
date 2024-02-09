package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/{postId}")
    public Like addLike(@PathVariable int postId, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            Post post = post.getId(postId);
            return likeService.addLike(post, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public Like removeLike(@PathVariable int postId, @RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            return likeService.removeLike();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @GetMapping("/{Id}")
    public List<Like> allPostLikes(@PathVariable int Id) {
        try {
            return likeService.allPostLikes(Id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{Id}")
    public int countLikes(@PathVariable int Id) {
        try {
            return likeService.countLikes(Id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    private void checkIsUserBlocked(User user) {
        if (user.isBlocked()) {
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }
//    private void isUserIsAlreadyLiked(User user, Post post) {
//        if (user.isBlocked()) {
//            throw new AuthorizationException(PERMISSION_ERROR);
//        }
//    }
}


