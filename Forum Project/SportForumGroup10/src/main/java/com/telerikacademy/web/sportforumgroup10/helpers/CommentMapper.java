package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.CommentService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class CommentMapper {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public CommentMapper(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }
    public Comment fromDto(int id, CommentDto dto) {
        Comment existingComment = commentService.getById(id);

        existingComment.setContent(dto.getContent());
        return existingComment;
    }

    public Comment fromDto(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setPost(postService.getById(commentDto.getPostId()));
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }
    public CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setContent(comment.getContent());
        return dto;
    }


}
