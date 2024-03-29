package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;

import java.util.List;

public interface CommentRepository {


    List<Comment> getByAuthor(int id);

    List<Comment> getByPost(int id);

    Comment getByCommentId(int id);

    Comment create(Comment comment);

    Comment update(Comment comment);

    Comment delete(int id);
}
