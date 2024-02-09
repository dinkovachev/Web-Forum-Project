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
    public Like addLike(int postId, User user) {
        Like like = new Like();
        return likeRepository.create(like);


    }
    @Override
    public Like removeLike(int likeId, User user){
    Like like = getById(likeId);
    checkModifyPermission(like, user);
        return likeRepository.remove(like);
    }

    @Override
    public Like getById(int likeId) {
        return likeRepository.getByLikeId(likeId);
    }

    @Override
    public int countLikes(int id) {
        return  likeRepository.countLikes(id);
    }

    @Override
    public List<Like> allPostLikes(int id) {
        return  likeRepository.postLikes(id);
    }

    private void checkModifyPermission(Like like, User requestUser) {
        if (!requestUser.isAdmin() && like.getAuthor_id().getId() != requestUser.getId()) {
            throw new AuthorizationException(LIKE_POST);
        }
    }
}
