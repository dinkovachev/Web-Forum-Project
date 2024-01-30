package com.telerikacademy.web.sportforumgroup10.models.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDto {

//    @NotNull(message = "Title can't be empty")
//    @Size(min = 16, max = 64, message = "Title should be between 16 and 64 symbols.")
//    private String comment;

    @NotNull(message = "Content can't be empty")
    @Size(min = 32, max = 8192, message = "Content should be between 32 and 8192 symbols.")
    private String content;

    public CommentDto() {
    }

//    public String getTitle() {
//       return title;
//    }

//    public void setTitle(String title) {
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
