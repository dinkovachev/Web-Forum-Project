package com.telerikacademy.web.sportforumgroup10.models.Dto;

import com.telerikacademy.web.sportforumgroup10.models.Post;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDto {

//    @NotNull (message = "Id can't be empty")
//    private User author;


    @NotNull(message = "Id can't be empty")
//    @Size(min = 16, max = 64, message = "Title should be between 16 and 64 symbols.")
    private int postId;

    @NotNull(message = "Content can't be empty")
    @Size(min = 32, max = 8192, message = "Content should be between 32 and 8192 symbols.")
    private String content;

    //todo add author

    public CommentDto() {

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postTitle) {
        this.postId = postTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
}
