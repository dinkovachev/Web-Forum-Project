package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityDuplicateException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.helpers.CommentMapper;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
//import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    public static final String PERMISSION_ERROR = "You don't have permission.";

    private final CommentService commentService;
    private final AuthenticationHelper authenticationHelper;
    private final CommentMapper commentMapper;


    @Autowired
    public CommentController(CommentService commentService, AuthenticationHelper authenticationHelper, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.authenticationHelper = authenticationHelper;

    }

//    @GetMapping()
//    public List<Comment> getAllByUser(
//        @RequestParam(required = false) String createdBy,
//        @RequestParam(required = false) String title,
//        @RequestParam(required = false) String content,
//        @RequestParam(required = false) String category,
//        @RequestParam(required = false) String minDate,
//        @RequestParam(required = false) String maxDate,
//        @RequestParam(required = false) String sortBy,
//        @RequestParam(required = false) String sortOrder) {
//        FilterOptions filterOptions = new FilterOptions(createdBy, title, content, category,minDate, maxDate, sortBy, sortOrder);
//        return commentService.getAllByUser(filterOptions);
//    }

    @GetMapping("/postId:{postId}")
    List<Comment> getByPost(@PathVariable int postId) {
        try {
            return commentService.getByPost(postId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }
    @GetMapping("/authorId:{authorId}")
    List<Comment> getByAuthor(@PathVariable int authorId) {
        try {
            return commentService.getByAuthor(authorId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }

    @PostMapping()
    public Comment create(@RequestHeader HttpHeaders headers, @Valid @RequestBody CommentDto commentDto) {
        try {
            User user =  authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            Comment comment = commentMapper.fromDtoIn(commentDto);
            comment.setAuthor(user);
            return commentService.create(comment, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PutMapping("/{commentId}")
    public Comment update(@RequestHeader HttpHeaders headers, @Valid @RequestBody CommentDto commentDto, @PathVariable int commentId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            return commentService.update(commentDto, user, commentId);
        } catch (EntityNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{commentId}")
    public Comment delete(@RequestHeader HttpHeaders headers, @PathVariable int commentId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkIsUserBlocked(user);
            return  commentService.delete(commentId, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    private void checkIsUserBlocked(User user) {
        if (user.isBlocked()) {
            throw new AuthorizationException(PERMISSION_ERROR);

        }
    }
}

