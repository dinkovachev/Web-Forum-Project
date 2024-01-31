package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.Like;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.LikeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LikeRepositoryImpl implements LikeRepository {

    private final SessionFactory sessionFactory;

    public LikeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Like getByLikeId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Like like = session.get(Like.class, id);
            if (like == null) {
                throw new EntityNotFoundException("Like", id);
            }
            return like;
        }
        }

    @Override
    public Like create(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(like);
            session.getTransaction().commit();
        }
        return like;
    }



    @Override
    public Like remove(int id) {
        Like like = getByLikeId(id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(like);
            session.getTransaction().commit();
        }
        return like;
    }

    @Override
    public List<Like> postLikes(int id) {
        return null;
    }

    @Override
    public int countLikes(int id) {
        return 0;
    }
}
