package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;

import java.util.List;

public interface LikeRepository {


    Like create (Like like);

    List<Like> postLikes(int id);

    Like getByLikeId(int id);


    int  countLikes (int id);

    Like remove(int id);
}
