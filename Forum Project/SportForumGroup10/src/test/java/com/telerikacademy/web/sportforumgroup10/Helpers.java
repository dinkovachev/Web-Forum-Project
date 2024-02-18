package com.telerikacademy.web.sportforumgroup10;

import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;

import java.time.LocalDateTime;

public class Helpers {
    public static User createMockAdmin() {
        User mockUser = createMockUser();
        mockUser.setAdmin(true);
        return mockUser;
    }

    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setFirstName("MockFirstName");
        mockUser.setLastName("MockLastName");
        mockUser.setEmail("MockEmail@mock.mock");
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setBlocked(false);
        mockUser.setDeleted(false);
        mockUser.setAdmin(false);
        return mockUser;
    }

    public static Post createMockPost() {
        var mockUser = createMockUser();
        var mockPost = new Post();
        mockPost.setId(1);
        mockPost.setTitle("MockTitle");
        mockPost.setContent("MockContent");
        mockPost.setCreatedAt(LocalDateTime.parse("15.02.2024"));
        mockPost.setDeleted(false);
        mockPost.setAuthor(mockUser);
        return mockPost;
    }

    public static UserFilterOptions createMockUserFilterOptions(){
        return new UserFilterOptions("name","email@email.com","username","sort","order");
    }
}
