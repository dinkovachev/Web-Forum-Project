package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
//import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface CommentRepository {

//    List<Comment> getAll(FilterOptions filterOptions);

    List<Comment> getByAuthor(int id);
    //todo ??

    List<Comment> getByPost(int id);
    //todo ??

    Comment getByCommentId(int id);

    Comment create (Comment comment);

    Comment update(Comment comment);

    Comment delete(int id);
}
