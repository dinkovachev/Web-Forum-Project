package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String USER_CONSTANT = "User";
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public User getById(int id) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null){
                throw new EntityNotFoundException(USER_CONSTANT, id);
            }
            return user;
        }
    }

    @Override
    public User getByFirstName(String firstName) {
        try(Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where firstName = :firstName", User.class);
                query.setParameter("first name", firstName);
                List<User> result = query.list();
                if (result.isEmpty()){
                    throw new EntityNotFoundException("User", "first name", firstName);
                }
                return result.get(0);
            }
    }

    @Override
    public User getByEmail(String email) {
        try(Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            List<User> result = query.list();
            if (result.isEmpty()){
                throw new EntityNotFoundException("User", "email", email);
            }
            return result.get(0);
        }
    }

    @Override
    public User getByUsername(String username) {
        try(Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            List<User> result = query.list();

            if (result.isEmpty()){
                throw new EntityNotFoundException("User", "username", username);
            }
            return result.get(0);
        }
    }


    @Override
    public User create(User user) {
        return null;
    }
}
