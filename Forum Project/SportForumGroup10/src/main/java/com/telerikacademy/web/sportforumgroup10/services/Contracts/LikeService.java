package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface LikeService {


    Like addLike(int postId, User user);

    Like removeLike(int like, User user);

    Like getById(int likeId);

    int countLikes(int id);

    List<Like> allPostLikes(int id);

}
