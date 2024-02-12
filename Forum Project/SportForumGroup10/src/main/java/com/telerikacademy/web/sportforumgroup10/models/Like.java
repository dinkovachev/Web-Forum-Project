package com.telerikacademy.web.sportforumgroup10.models;

import jakarta.persistence.*;


@Entity
    @Table(name = "likes")
    public class Like {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "like_id")
        private int likeId;
        @ManyToOne
        @JoinColumn(name = "liked_post_id")
        private Post likedPost;
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


        public Post getLikedPost() {
            return likedPost;
        }


        public void setLikedPost(Post likedPost) {
            this.likedPost = likedPost;
        }


        public User getAuthor_id() {
            return author_id;
        }


        public void setAuthor_id(User author_id) {
            this.author_id = author_id;
        }
    }
