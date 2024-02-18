package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.exceptions.UnauthorizedOperationException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.helpers.CommentMapper;
import com.telerikacademy.web.sportforumgroup10.helpers.PostMapper;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.CommentService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/comment")
public class CommentMvcController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public CommentMvcController(PostService postService, PostMapper postMapper,
                                CommentService commentService, CommentMapper commentMapper,
                                AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
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


    @PostMapping("/{id}")
    public String addComment(@PathVariable int id,
                             @Valid @ModelAttribute CommentDto commentDto,
                             Model model,
                             HttpSession httpSession) {
        //todo extract method authentication
        User user;
        Post post;
       Comment comment;
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
            //todo fix next line in html
            commentDto.setPostId(id);
            comment = commentMapper.fromDto(commentDto);
            post = postService.getById(id);

            comment.setPost(post);
            comment.setAuthor(user);

            commentService.create(id, comment, user);
            return "redirect:/posts/" + id;
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @PostMapping("/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable int id,
                                @PathVariable int commentId,
                                @Valid @ModelAttribute CommentDto commentDto,
                                Model model,
                                BindingResult result,
                                HttpSession httpSession) {

        User user;
        Comment comment;

        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        }
        if (result.hasErrors()) {
            return "CommentUpdateView";
        }

        try {
            comment = commentMapper.fromDto(commentId, commentDto);
            commentService.update(comment, user);
            return "redirect:/posts/{id}";
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @GetMapping("/{id}/comment/{commentId}/update")
    public String showCommentEditPage(@PathVariable int id,
                                      @PathVariable int commentId,
                                      Model model,
                                      HttpSession httpSession) {
        try {
            authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            Comment comment = commentService.getById(commentId);
            CommentDto commentDto = commentMapper.toDto(comment);
            model.addAttribute("commentId", commentId);
            model.addAttribute("comment", commentDto);
            return "CommentUpdateView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @GetMapping("/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable int id,
                                @PathVariable int commentId,
                                Model model,
                                HttpSession httpSession) {
        User user;
//        Comment comment = commentService.getById(commentId);
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            commentService.delete(commentId, user);
            return "redirect:/posts/" + id;
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
}


