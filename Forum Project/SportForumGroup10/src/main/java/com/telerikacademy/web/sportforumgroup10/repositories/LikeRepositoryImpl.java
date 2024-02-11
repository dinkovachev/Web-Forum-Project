package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.LikeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LikeRepositoryImpl implements LikeRepository {

    private final SessionFactory sessionFactory;

    public LikeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Like getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Like like = session.get(Like.class, id);
            if (like == null) {
                throw new EntityNotFoundException("Like", id);
            }
            return like;
        }
    }

    @Override
    public Like save(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(like);
            session.getTransaction().commit();
        }
        return like;
    }


    @Override
    public Like remove(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(like);
            session.getTransaction().commit();
        }
        return like;
    }

    @Override
    public List<Like> getPostLikes(int postId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Like> query = session.createQuery("FROM Like where likedPost.id = :postId", Like.class);
            query.setParameter("postId", postId);
            List<Like> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Like", "likedPostId", postId);
            }
            return result;
        }
    }

    @Override
    public int countByPost(int postId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Like> query = session.createQuery("SELECT COUNT(*) FROM Like where likedPost.id = :postId", Like.class);
            query.setParameter("postId", postId);
            List<Like> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Like", "likedPostId", postId);
            }
            return result.size();
        }
    }
}
