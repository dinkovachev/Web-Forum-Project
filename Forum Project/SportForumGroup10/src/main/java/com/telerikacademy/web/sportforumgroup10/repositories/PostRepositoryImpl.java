package com.telerikacademy.web.sportforumgroup10.repositories;

import com.telerikacademy.web.sportforumgroup10.models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PostRepositoryImpl implements PostRepository {
  private final SessionFactory sessionFactory;


  @Autowired
    public PostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

   @Override
   public List<Post>  getAllPosts() {
      try (Session session = sessionFactory.openSession()){
          Query<Post> query = session.createQuery("from Post", Post.class);
          return query.list();
      }
   }


}
