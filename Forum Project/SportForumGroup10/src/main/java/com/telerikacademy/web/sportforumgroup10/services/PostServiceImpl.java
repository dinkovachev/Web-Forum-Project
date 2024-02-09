package com.telerikacademy.web.sportforumgroup10.services;


import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.PostFilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.PostRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    public static final String PERMISSION_ERROR = "You don't have permission.";
    public static final String MODIFY_THE_POST = "Only Admin or creator can modify the post.";
    private final PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Post> getAll(PostFilterOptions postFilterOptions) {
        return repository.getAll(postFilterOptions);
    }

    @Override
    public Post getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void create(Post post, User creator) {
        isUserBlocked(creator);
        repository.create(post);
    }

    @Override
    public Post update(PostDto postDto, User user, int postId) {
        checkModifyPermission(postId, user);

        Post post = getById(postId);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return repository.update(post);
    }

    @Override
    public void delete(int id, User user) {
        checkModifyPermission(id, user);
        repository.delete(id);
    }

    private void checkModifyPermission(int id, User user) {
        Post post = getById(id);
        if (!user.isAdmin() && !post.getAuthor().equals(user)) {
            throw new AuthorizationException(MODIFY_THE_POST);
        }
    }


    private void isUserBlocked(User creator) {
        if (creator.isBlocked()) {
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }


}
