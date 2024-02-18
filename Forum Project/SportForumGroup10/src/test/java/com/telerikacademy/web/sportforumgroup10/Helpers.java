package com.telerikacademy.web.sportforumgroup10;

import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.PostFilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;

import java.sql.Timestamp;
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
        mockPost.setDeleted(false);
        mockPost.setAuthor(mockUser);
        return mockPost;
    }

    public static UserFilterOptions createMockUserFilterOptions(){
        return new UserFilterOptions("name","email@email.com","username","sort","order");
    }
    public static PostDto createMockDtoForUpdate() {
        PostDto postDto = new PostDto();
        postDto.setContent("Content12345678910");
        postDto.setTitle("Title1234567891011");
        return postDto;
    }

    public static PostFilterOptions createMockPostFilterOptions(){
        return new PostFilterOptions("title","author","sortBy","orderBy" );
    }




}
