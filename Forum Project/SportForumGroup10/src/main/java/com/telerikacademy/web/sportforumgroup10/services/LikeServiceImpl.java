package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.LikeRepository;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.PostRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public static final String LIKE_POST = "Only registered user can like the post.";


    @Autowired
    public LikeServiceImpl(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void addLike(Post post, User user) {

    }
    @Override
    public void removeLike(Post post, User user) {
    }

    @Override
    public int countLikes(Post post, User user) {
        return 0;
    }

//    @Override
//    public List<Like> allPostLikes(Post post, User user) {
//        return List<Like>;
//    }

    private void checkModifyPermission(Comment comment, User requestUser) {
        if (!requestUser.isAdmin() && comment.getAuthor().getId() != requestUser.getId()) {
            throw new AuthorizationException(LIKE_POST);
        }
    }
}
