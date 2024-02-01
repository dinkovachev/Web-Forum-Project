package com.telerikacademy.web.sportforumgroup10.services.Contracts;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;

import java.util.List;

public interface LikeService {


    void addLike(Post post, User user);

    void removeLike(Post post, User user);

    int countLikes(Post post, User user);
//    List<Like> allPostLikes(Post post, User user);

}
