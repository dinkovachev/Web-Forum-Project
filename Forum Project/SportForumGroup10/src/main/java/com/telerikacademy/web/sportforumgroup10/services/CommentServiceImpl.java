package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.CommentRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class CommentServiceImpl implements CommentService {


    public static final String PERMISSION_ERROR = "You don't have permission.";
    public static final String MODIFY_THE_POST = "Only Admin or creator can modify the comment.";
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAll(FilterOptions filterOptions) {
        return commentRepository.getAll(filterOptions);
    }

    @Override
    public Comment getByAuthor(String name) {
        return commentRepository.getByAuthor(name);
    }
    @Override
    public Comment getByPost(int id) {
        return commentRepository.getByPost(id);
    }

    @Override
    public void create(Comment comment, User creator) {
        isUserBlocked(creator);

        commentRepository.create(comment);
    }

    @Override
    public Comment update(CommentDto commentDto, User user, int postId) {
        checkModifyPermission(postId, user);

        Comment comment = getByPost(postId);
        comment.setContent(commentDto.getContent());
        return commentRepository.update(comment);
    }

    @Override
    public void delete(int id, User user) {
        checkModifyPermission(id, user);

        commentRepository.delete(id);
    }

    private void checkModifyPermission(int id, User user) {
        Comment comment = getByPost(id);
        if (!user.isAdmin() && !comment.getAuthor().equals(user)) {
            throw new AuthorizationException(MODIFY_THE_POST);
        }
    }


    private void isUserBlocked(User creator) {
        if (creator.isBlocked()) {
            throw new AuthorizationException(PERMISSION_ERROR);

        }
    }


}
