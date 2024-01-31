package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.exceptions.EntityNotFoundException;
import com.telerikacademy.web.sportforumgroup10.models.Comment;
import com.telerikacademy.web.sportforumgroup10.models.FilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.CommentRepository;
import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CommentRepositoryImpl implements CommentRepository {


    private final SessionFactory sessionFactory;

    //todo finish methods
    @Autowired
    public CommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    @Override
//    public List<Comment> getAll(FilterOptions filterOptions) {
//        try (Session session = sessionFactory.openSession()) {
//            List<String> filters = new ArrayList<>();
//            Map<String, Object> params = new HashMap<>();
//
//            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                    .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
//                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
//                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
//                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
//                    .toFormatter();
//
//            filterOptions.getTitle().ifPresent(value -> {
//                filters.add("title like :title");
//                params.put("title", String.format("%%%s%%", value));
//            });
//
//
//            filterOptions.getContent().ifPresent(value -> {
//                filters.add("content like :content");
//                params.put("content", String.format("%%%s%%", value));
//            });
//
//
//            filterOptions.getCreatedBy().ifPresent(value -> {
//                filters.add("createdBy.username like :createdBy");
//                params.put("createdBy", String.format("%%%s%%", value));
//            });
//
//
//            filterOptions.getMinDate().ifPresent(value -> {
//                if (!value.isEmpty()) {
//                    filters.add("createdAt >= :minDate");
//                    params.put("minDate", LocalDateTime.parse(value, formatter));
//                }
//            });
//
//            filterOptions.getMaxDate().ifPresent(value -> {
//                if (!value.isEmpty()) {
//                    filters.add("createdAt <= :maxDate");
//                    params.put("maxDate", LocalDateTime.parse(value, formatter));
//                }
//            });
//
//
//            StringBuilder queryString = new StringBuilder("from Comment");
//
//            if (!filters.isEmpty()) {
//                queryString.append(" where ").append(String.join(" and ", filters));
//            }
//
//            if (filterOptions.getSortBy().isPresent()) {
//                if (!filterOptions.getSortBy().get().isEmpty()) {
//                    queryString.append(generateOrderBy(filterOptions));
//                }
//            }
//            Query<Comment> query = session.createQuery(queryString.toString(), Comment.class);
//            query.setProperties(params);
//            return query.list();
//        }
//    }

//    public String generateOrderBy(FilterOptions postFilterOptions) {
//        if (postFilterOptions.getSortBy().isEmpty()) {
//            return "";
//        }
//
//        String orderBy = "";
//        switch (postFilterOptions.getSortBy().get()) {
//            case "title":
//                orderBy = "title";
//                break;
//            case "content":
//                orderBy = "content";
//
//        }
//
//        orderBy = String.format(" order by %s", orderBy);
//
//        if (postFilterOptions.getSortOrder().isPresent() && postFilterOptions.getSortOrder().get().equalsIgnoreCase("desc")) {
//            orderBy = String.format("%s desc", orderBy);
//        }
//
//        return orderBy;
//    }

    //todo get By Author and post
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
            Query<Comment> query = session.createQuery("from Comment where post.id =:id", Comment.class);
            query.setParameter("id", id);
            List<Comment> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Comment", id);
            }


            return result;
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
