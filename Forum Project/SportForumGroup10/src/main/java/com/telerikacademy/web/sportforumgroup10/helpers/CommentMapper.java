package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Dto.CommentDto;
import com.telerikacademy.web.sportforumgroup10.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class CommentMapper {
    public Comment fromDtoIn(CommentDto commentDto, User creator) {
        Comment comment = new Comment();
        //           comment.setTitle(commentDto.getTitle());
        comment.setContent(commentDto.getContent());
        comment.setAuthor(creator);
        comment.setCreatedAt(LocalDateTime.now());
        return comment;

    }
}
