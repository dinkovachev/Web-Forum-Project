package com.telerikacademy.web.sportforumgroup10.repositories.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;

import java.util.List;

public interface LikeRepository {


    void create (Like like);

    List<Like> allPostLikes(int id);


    int  countLikes (int id);

    void remove(int id);
}
