package com.telerikacademy.web.sportforumgroup10.models;

import java.util.Optional;

public class UserFilterOptions {

    private final Optional<String> firstName;
    private final Optional<String> email;
    private final Optional<String> username;
    //TODO double check this since there is no post.id field in User to search from here
//    private Optional<Integer> postId;
    private final Optional<String> sortBy;
    private final Optional<String> orderBy;

    public UserFilterOptions(String firstName, String email,
                             String username, String sortBy, String orderBy) {
        this.firstName = Optional.ofNullable(firstName);
        this.email = Optional.ofNullable(email);
        this.username = Optional.ofNullable(username);
//        this.postId = Optional.ofNullable(postId);
        this.sortBy = Optional.ofNullable(sortBy);
        this.orderBy = Optional.ofNullable(orderBy);
    }

    public Optional<String> getFirstName() {
        return firstName;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public Optional<String> getOrderBy() {
        return orderBy;
    }

//    public Optional<Integer> getPostId() {
//        return postId;
//    }
}
