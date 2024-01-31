package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class CommentMapper {

    private final PostService postService;

    public CommentMapper(PostService postService) {
        this.postService = postService;
    }

    public Comment fromDtoIn(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setPost(postService.getById(commentDto.getPostId()));
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;

    }
}
