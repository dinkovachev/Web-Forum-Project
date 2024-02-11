package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface LikeService {


    Like createLike(int postId, User user);

    Like removeLike(int like, User user);

    Like getById(int likeId);

    int countLikes(int postId);

    List<Like> allPostLikes(int postId);

}
