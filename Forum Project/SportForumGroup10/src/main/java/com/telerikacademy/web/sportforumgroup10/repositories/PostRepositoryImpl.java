package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.PostFilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.PostRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;


@Repository
public class PostRepositoryImpl implements PostRepository {
    private final SessionFactory sessionFactory;


    @Autowired
    public PostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Post> getAll(PostFilterOptions postFilterOptions) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            StringBuilder queryString = new StringBuilder("FROM Post");

            postFilterOptions.getTitle().ifPresent(value -> {
                filters.add("title like :title");
                params.put("title", String.format("%%%s%%", value));
            });

            postFilterOptions.getCreatedBy().ifPresent(value -> {
                filters.add("createdBy.username like :createdBy");
                params.put("createdBy", String.format("%%%s%%", value));
            });


            if (!filters.isEmpty()) {
                queryString.append(" where ").append(String.join(" and ", filters));
                queryString.append(" isDeleted=false ");
            } else {
                queryString.append(" where isDeleted=false ");
            }
            queryString.append(generateOrderBy(postFilterOptions));

            Query<Post> query = session.createQuery(queryString.toString(), Post.class);
            query.setProperties(params);
            return query.list();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Post not found");
        }
    }

    public String generateOrderBy(PostFilterOptions postFilterOptions) {
        if (postFilterOptions.getSortBy().isEmpty()) {
            return "";
        }

        String orderBy = "";
        switch (postFilterOptions.getSortBy().get()) {
            case "title":
                orderBy = "title";
                break;
            case "username":
                orderBy = "username";
                break;
            default:
                return "";

        }

        orderBy = String.format(" order by %s", orderBy);

        if (postFilterOptions.getOrderBy().isPresent()
                && postFilterOptions.getOrderBy().get().equalsIgnoreCase("desc")) {
            orderBy = String.format("%s desc", orderBy);
        }

        return orderBy;
    }

    @Override
    public Post getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Post post = session.get(Post.class, id);
            if (post == null) {
                throw new EntityNotFoundException("Post", id);
            }
            return post;
        }
    }

    @Override
    public void create(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public Post update(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();
        }
        return post;
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            deleteComments(id);
            session.getTransaction();
            session.remove(id);
            session.getTransaction().commit();
        }
    }

    private void deleteComments(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<?> query = session.createNativeQuery("delete from comments where post_id= :id", Post.class);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Post> getTop10MostCommentedPost() {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery("SELECT p " +
                    "FROM Post p " +
                    "WHERE  p.isDeleted=false " +
                    "ORDER BY SIZE(p.comments) DESC", Post.class
            );

            query.setMaxResults(10);
            return query.list();
        }
    }

    @Override
    public List<Post> get10MostRecentlyCreatedPosts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery("SELECT p " +
                    "FROM Post p " +
                    "WHERE  p.isDeleted=false " +
                    "ORDER BY p.createdAt DESC", Post.class
            );
            query.setMaxResults(10);
            return query.list();
        }
    }

    @Override
    public void modifyLike(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();
        }
    }
    @Override
    public long getPostCount() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(*) FROM Post WHERE isDeleted=false ";
            Query<Long> query = session.createQuery(hql, Long.class);
            List<Long> resultList = query.list();

            return resultList.get(0);
        }
    }

}
