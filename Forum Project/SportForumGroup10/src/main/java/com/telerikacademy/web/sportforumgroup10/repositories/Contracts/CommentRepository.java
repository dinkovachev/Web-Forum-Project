package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAll(FilterOptions filterOptions);

    Comment getByAuthor(String name);
    //todo ??

    Comment getByPost(int id);
    //todo ??

    void create (Comment comment);

    Comment update(Comment comment);

    void delete(int id);
}
