package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.Post;

import java.util.List;

public interface PostRepository {


    List<Post> getAll(FilterOptions filterOptions);

    Post getById(int id);

    void create(Post post);

    Post update(Post post);

    void delete(int id);
}
