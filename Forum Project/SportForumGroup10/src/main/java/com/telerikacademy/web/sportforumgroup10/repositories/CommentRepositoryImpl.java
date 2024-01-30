package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.CommentRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommentRepositoryImpl implements CommentRepository {


    private final SessionFactory sessionFactory;


    @Autowired
    public CommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Comment> getAll(FilterOptions filterOptions) {
        return null;
    }

    @Override
    public Comment getByAuthor(String name) {
        return null;
    }

    @Override
    public Comment getByPost(int id) {
        return null;
    }

    @Override
    public void create(Comment comment) {

    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
