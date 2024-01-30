package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface CommentService {


    List<Comment> getAll(FilterOptions filterOptions);


    Comment getByAuthor(String name);
    //todo ??

    Comment getByPost(int id);
    //todo ??

    void create(Comment comment, User creator);

    Comment update(CommentDto commentDto, User user, int postId);

    void delete(int id, User user);


}
