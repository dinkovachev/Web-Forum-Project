package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.CommentRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {


    public static final String PERMISSION_ERROR = "You don't have permission.";
    public static final String MODIFY_THE_POST = "Only Admin or creator can modify the comment.";
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

//    @Override
//    public List<Comment> getAllByUser(FilterOptions filterOptions) {
//        return commentRepository.getAll(filterOptions);
//    }

    @Override
    public List<Comment> getByAuthor(int id) {
        return commentRepository.getByAuthor(id);
    }

    @Override
    public List<Comment> getByPost(int id) {
        return commentRepository.getByPost(id);
    }

    @Override
    public Comment getById(int id) {
        return commentRepository.getByCommentId(id);
    }


    @Override
    public Comment create(Comment comment, User user) {
        return commentRepository.create(comment);
    }


    @Override
    public Comment update(CommentDto commentDto, User user, int commentId) {
        Comment comment = getById(commentId);
        checkModifyPermission(comment, user);
        comment.setContent(commentDto.getContent());

        return commentRepository.update(comment);
    }

    @Override
    public Comment delete(int commentId, User user) {
        Comment comment = getById(commentId);
        checkModifyPermission(comment, user);
        return commentRepository.delete(commentId);
    }

    private void checkModifyPermission(Comment comment, User requestUser) {
        if (!requestUser.isAdmin() && comment.getAuthor().getId() != requestUser.getId()) {
            throw new AuthorizationException(MODIFY_THE_POST);
        }
    }


}
