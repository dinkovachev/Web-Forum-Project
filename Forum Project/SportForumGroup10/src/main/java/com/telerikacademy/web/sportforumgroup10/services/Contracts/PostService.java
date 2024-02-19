package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.PostFilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface PostService {

    List<Post> getAll(PostFilterOptions postFilterOptions);

    Post getById(int id);
    void create (Post post, User creator);

    Post update(Post post, User user);

    void delete(int id, User user);

    List<Post> getTop10MostCommentedPosts();

    List<Post> get10MostRecentlyCreatedPosts();

    long getPostCount();
}
