package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;
    private  final AuthenticationHelper helper;
    @Autowired
    public PostController(PostService service, AuthenticationHelper helper) {
        this.service = service;
        this.helper = helper;
    }


    @GetMapping
    List<Post> getAllPosts(@RequestHeader HttpHeaders headers){
        try {
        User user = helper.tryGetUser(headers);
        return service.getAllPosts();
        }catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());

        }
    }


}
