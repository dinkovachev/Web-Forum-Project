package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Like;

import java.util.List;

public interface LikeRepository {


    Like save(Like like);

    List<Like> getPostLikes(int postId);

    Like getById(int id);


    int countByPost(int postId);

    Like remove(Like like);
}
