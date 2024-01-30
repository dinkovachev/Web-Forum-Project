package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMapper {
     public Post fromDtoIn(PostDto postDto, User creator) {
         Post post = new Post();
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
         post.setAuthor(creator);
         post.setCreatedAt(LocalDateTime.now());
         return post;

     }
}
