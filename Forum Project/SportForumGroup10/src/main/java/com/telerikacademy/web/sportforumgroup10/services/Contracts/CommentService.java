package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;

import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface CommentService {




    List<Comment> getByAuthor(int id);

    List<Comment> getByPost(int id);

    Comment getById(int id);

    Comment create(int postId,Comment comment, User user);

    Comment update(Comment comment, User user);

    Comment delete(int commentId, User user);


}
