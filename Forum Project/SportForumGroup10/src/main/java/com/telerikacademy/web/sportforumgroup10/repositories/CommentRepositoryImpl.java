package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.CommentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class CommentRepositoryImpl implements CommentRepository {


    private final SessionFactory sessionFactory;


    @Autowired
    public CommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Comment> getByAuthor(int id) {
        try (Session session = sessionFactory.openSession()) {

            Query<Comment> query = session.createQuery("from Comment where author.id = :id", Comment.class);
            query.setParameter("id", id);
            List<Comment> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Author", id);
            }
            return result;
        }
    }

    @Override
    public List<Comment> getByPost(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("from Comment where post.id =:id and isDeleted=false", Comment.class);
            query.setParameter("id", id);
            List<Comment> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Comment", id);
            }
            //todo fix filter in query
            return result.stream().filter(comment -> !comment.isDeleted()).collect(Collectors.toList());
        }
    }


    @Override
    public Comment getByCommentId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Comment comment = session.get(Comment.class, id);
            if (comment == null) {
                throw new EntityNotFoundException("Comment", id);
            }
            return comment;
        }
    }


    @Override
    public Comment create(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(comment);
            session.getTransaction().commit();
        } return comment;
    }


    @Override
    public Comment update(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(comment);
            session.getTransaction().commit();
        }
        return comment;
    }


    @Override
    public Comment delete(int id) {
        Comment comment = getByCommentId(id);
        comment.setDeleted(true);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(comment);
            session.getTransaction().commit();
        }
        return comment;
    }
}
