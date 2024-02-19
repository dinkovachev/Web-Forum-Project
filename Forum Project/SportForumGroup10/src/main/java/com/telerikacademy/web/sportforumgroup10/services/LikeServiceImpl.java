package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.LikeRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.LikeService;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private final PostService postService;
    private final LikeRepository likeRepository;

    public static final String REMOVE_LIKE_ERROR_MSG = "Only like author can remove like from the post.";


    @Autowired
    public LikeServiceImpl(PostService postService, LikeRepository likeRepository) {
        this.postService = postService;
        this.likeRepository = likeRepository;
    }


    @Override
    public Like createLike(int postId, User user) {
        Like like = new Like();
        like.setAuthor(user);
        like.setLikedPost(postService.getById(postId));
        return likeRepository.save(like);
    }


    @Override
    public Like removeLike(int postId, User user) {
        Like like = likeRepository.getByPostAndAuthor(postId, user.getId());
        checkModifyPermission(like, user);
        return likeRepository.remove(like);
    }


    @Override
    public Like getById(int likeId) {
        return likeRepository.getById(likeId);
    }


    @Override
    public int countLikes(int postId) {
        return likeRepository.countByPost(postId);
    }


    @Override
    public List<Like> allPostLikes(int postId) {
        return likeRepository.getPostLikes(postId);
    }


    private void checkModifyPermission(Like like, User requestUser) {
        if (like.getAuthor().getId() != requestUser.getId()) {
            throw new AuthorizationException(REMOVE_LIKE_ERROR_MSG);
        }
    }
}
