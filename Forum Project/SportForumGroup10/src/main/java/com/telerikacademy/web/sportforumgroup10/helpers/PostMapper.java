package com.telerikacademy.web.sportforumgroup10.helpers;

import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class PostMapper {

    private final PostService postService;

    @Autowired
    public PostMapper(PostService postService) {
        this.postService= postService;
    }


     public Post fromDtoIn(PostDto postDto, User creator) {
         Post post = new Post();
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
         post.setAuthor(creator);
         post.setCreatedAt(Timestamp.from(Instant.now()));
         return post;

     }
    public Post fromDto(PostDto dto) {
        Post post = new Post();

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }
    public Post fromDto(int id, PostDto dto) {
        Post existingPost = postService.getById(id);

        existingPost.setTitle(dto.getTitle());
        existingPost.setContent(dto.getContent());

        return existingPost;
    }

    public PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        return dto;
    }
}

