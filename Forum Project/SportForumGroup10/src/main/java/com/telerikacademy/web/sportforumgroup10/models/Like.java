package com.telerikacademy.web.sportforumgroup10.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;


    @Entity
    @Table(name = "likes")
    public class Like {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "like_id")
        private int likeId;

        @ManyToOne
        @JoinColumn(name = "likedPostId")
        private Post likedPostId;
        @ManyToOne
        @JoinColumn(name = "author_id")
        private User author_id;

        public Like() {
        }

        public int getLikeId() {
            return likeId;
        }

        public void setLikeId(int likeId) {
            this.likeId = likeId;
        }

        public Post getLikedPostId() {
            return likedPostId;
        }

        public void setLikedPostId(Post likedPostId) {
            this.likedPostId = likedPostId;
        }

        public User getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(User author_id) {
            this.author_id = author_id;
        }
    }
