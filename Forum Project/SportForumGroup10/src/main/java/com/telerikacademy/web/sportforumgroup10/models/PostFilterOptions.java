package com.telerikacademy.web.sportforumgroup10.models;

import java.util.Optional;

public class PostFilterOptions {
    private Optional<String> createdBy;
    private Optional<String> title;
    private Optional<String> content;
    private Optional<String> comment;
    private Optional<String> sortBy;
    private Optional<String> orderBy;

    public PostFilterOptions(
            String createdBy,
            String title,
            String content,
            String sortBy,
            String orderBy
    ) {
        this.createdBy = Optional.ofNullable(createdBy);
        this.title = Optional.ofNullable(title);
        this.content = Optional.ofNullable(content);
        this.sortBy = Optional.ofNullable(sortBy);
        this.orderBy = Optional.ofNullable(orderBy);
    }

    public PostFilterOptions(
            String title,
            String createdBy,
            String sortBy,
            String orderBy) {
        this.createdBy = Optional.ofNullable(createdBy);
        this.title = Optional.ofNullable(title);
        this.sortBy = Optional.ofNullable(sortBy);
        this.orderBy = Optional.ofNullable(orderBy);
    }


    public Optional<String> getCreatedBy() {
        return createdBy;
    }

    public Optional<String> getTitle() {
        return title;
    }

    public Optional<String> getContent() {
        return content;
    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public Optional<String> getOrderBy() {
        return orderBy;
    }
}