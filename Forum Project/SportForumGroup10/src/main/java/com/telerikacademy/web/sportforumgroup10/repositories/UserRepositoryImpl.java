package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityAlreadyAdminException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityDeletedException;
import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String USER_CONSTANT = "User";
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers(UserFilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            StringBuilder queryString = new StringBuilder(" from User ");

            filterOptions.getFirstName().ifPresent(value -> {
                filters.add(" firstName like :firstName ");
                params.put("firstName", String.format("%%%s%%", value));
            });
            filterOptions.getEmail().ifPresent(value -> {
                filters.add(" email like :email ");
                params.put("email", String.format("%%%s%%", value));
            });
            filterOptions.getUsername().ifPresent(value -> {
                filters.add(" username like :username ");
                params.put("username", String.format("%%%s%%", value));
            });

            if (!filters.isEmpty()) {
                queryString.append("where").append(String.join(" and ", filters));
            }
            queryString.append(generateOrderBy(filterOptions));

            Query<User> query = session.createQuery(queryString.toString(), User.class);
            query.setProperties(params);
            return query.list();
        }
    }

    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new EntityNotFoundException(USER_CONSTANT, id);
            }
            return user;
        }
    }

    @Override
    public User getByFirstName(String firstName) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where firstName = :firstName", User.class);
            query.setParameter("firstName", firstName);
            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "first name", firstName);
            }
            return result.get(0);
        }
    }

    @Override
    public User getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "email", email);
            }
            return result.get(0);
        }
    }

    @Override
    public User getByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            List<User> result = query.list();

            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "username", username);
            }
            return result.get(0);
        }
    }


    @Override
    public User create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User update(User user) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User delete(int id) {
        User userToDelete = getById(id);
        if (userToDelete.isDeleted()) {
            throw new EntityDeletedException("User", "username", userToDelete.getUsername());
        }
        userToDelete.setDeleted(true);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToDelete);
            session.getTransaction().commit();
        }
        return userToDelete;
    }

    @Override
    public User makeUserAdmin(int id) {
        User userToMakeAdmin = getById(id);
        if (userToMakeAdmin.isAdmin()){
            throw new EntityAlreadyAdminException("User", "username", userToMakeAdmin.getUsername());
        }
        userToMakeAdmin.setAdmin(true);
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToMakeAdmin);
            session.getTransaction().commit();
        }
        return userToMakeAdmin;
    }

    @Override
    public User unmakeUserAdmin(int id) {
        User userToMakeAdmin = getById(id);
        userToMakeAdmin.setAdmin(false);
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToMakeAdmin);
            session.getTransaction().commit();
        }
        return userToMakeAdmin;
    }

    private String generateOrderBy(UserFilterOptions filterOptions) {
        if (filterOptions.getSortBy().isEmpty()) {
            return "";
        }

        String orderBy = "";
        switch (filterOptions.getSortBy().get()) {
            case "firstName":
                orderBy = "firstName";
                break;
            case "email":
                orderBy = "email";
                break;
            case "username":
                orderBy = "username";
                break;
            default:
                return "";
        }

        orderBy = String.format(" order by %s", orderBy);

        if (filterOptions.getOrderBy().isPresent() && filterOptions.getOrderBy().get().equalsIgnoreCase("desc")) {
            orderBy = String.format("%s desc", orderBy);
        }
        return orderBy;
    }
}
