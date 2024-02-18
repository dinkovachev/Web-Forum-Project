package com.telerikacademy.web.sportforumgroup10.models.Dto;

public class PostFilterDto {

    private String title;
    private String username;
    private String content;
    private String sortBy;
    private String orderBy;

    public PostFilterDto() {
    }

    public PostFilterDto(String title, String username, String content, String sortBy, String orderBy) {
        this.title = title;
        this.username = username;
        this.content = content;
        this.sortBy = sortBy;
        this.orderBy = orderBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}

