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

            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();

            postFilterOptions.getTitle().ifPresent(value -> {
                filters.add("title like :title");
                params.put("title", String.format("%%%s%%", value));
            });


            postFilterOptions.getContent().ifPresent(value -> {
                filters.add("content like :content");
                params.put("content", String.format("%%%s%%", value));
            });


            postFilterOptions.getCreatedBy().ifPresent(value -> {
                filters.add("createdBy.username like :createdBy");
                params.put("createdBy", String.format("%%%s%%", value));
            });

            postFilterOptions.getMinDate().ifPresent(value -> {
                if (!value.isEmpty()) {
                    filters.add("createdAt >= :minDate");
                    params.put("minDate", LocalDateTime.parse(value, formatter));
                }
            });

            postFilterOptions.getMaxDate().ifPresent(value -> {
                if (!value.isEmpty()) {
                    filters.add("createdAt <= :maxDate");
                    params.put("maxDate", LocalDateTime.parse(value, formatter));
                }
            });


            StringBuilder queryString = new StringBuilder("FROM Post");

            if (!filters.isEmpty()) {
                queryString.append(" where ").append(String.join(" and ", filters));
            }

            if (postFilterOptions.getSortBy().isPresent()) {
                if (!postFilterOptions.getSortBy().get().isEmpty()) {
                    queryString.append(generateOrderBy(postFilterOptions));
                }
            }
            Query<Post> query = session.createQuery(queryString.toString(), Post.class);
            query.setProperties(params);
            return query.list();
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
            case "content":
                orderBy = "content";

        }

        orderBy = String.format(" order by %s", orderBy);

        if (postFilterOptions.getSortOrder().isPresent() && postFilterOptions.getSortOrder().get().equalsIgnoreCase("desc")) {
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
}
