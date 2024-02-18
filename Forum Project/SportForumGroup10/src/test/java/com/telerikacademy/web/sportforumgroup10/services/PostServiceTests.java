package com.telerikacademy.web.sportforumgroup10.services;
import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.models.Dto.PostDto;
import com.telerikacademy.web.sportforumgroup10.models.Post;
import com.telerikacademy.web.sportforumgroup10.models.PostFilterOptions;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.PostRepository;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.web.sportforumgroup10.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository mockRepository;

    @InjectMocks
    PostServiceImpl mockService;

    @BeforeEach
    public void setUp() {
        mockService = new PostServiceImpl(mockRepository);
    }

@Test
    void getAll_Should_Call_Repository(){
    PostFilterOptions mockFilterOptions = createMockPostFilterOptions();
    Mockito.when(mockRepository.getAll(mockFilterOptions))
            .thenReturn(null);

    // Act
    mockService.getAll(mockFilterOptions);

    // Assert
    Mockito.verify(mockRepository, Mockito.times(1))
            .getAll(mockFilterOptions);
}


    @Test
    public void getById_Should_ReturnPost_When_MatchByIdExist() {
        //Arrange
        Post post = createMockPost();
        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        Post result = mockService.getById(post.getId());

        //Assert
        Assertions.assertEquals(post, result);
    }

    @Test
    public void create_Should_CallRepository_When_Arguments_AreValid() {
        //Arrange
        Post post = createMockPost();

        User user = createMockUser();

        //Act
        mockService.create(post, user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .create(post);
    }

    @Test
    public void update_Should_CallRepository_When_UserIsCreator() {
        //Arrange
        User user = createMockUser();

        Post post = createMockPost();

        PostDto postDto = new PostDto();
        postDto.setContent("content");
        postDto.setTitle("title");

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);


        //Act
        mockService.update(postDto, user, post.getId());

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .update(post);
    }

    @Test
    public void update_Should_CallRepository_When_UserIsAdmin() {
        //Arrange
        User user = createMockUser();

        Post post = createMockPost();

        PostDto postDto = new PostDto();
        postDto.setContent("content");
        postDto.setTitle("title");

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);


        //Act
        mockService.update(postDto, user, post.getId());

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .update(post);
    }

    @Test
    public void update_Should_CallRepository_When_UpdatingExistingPost() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();

        PostDto postDto = createMockDtoForUpdate();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        mockService.update(postDto, user, post.getId());

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .update(post);
    }
    @Test
    public void update_Should_CallRepository_When_UserIsNotCreatorOrAdmin() {
        //Arrange
        Post post = createMockPost();
        PostDto postDto = createMockDtoForUpdate();
        User user = createMockUser();
        user.setId(2);

        Mockito.when(mockRepository.getById(post.getId()))
                .thenReturn(post);

        //Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> mockService.update(postDto, user, post.getId()));
    }

    @Test
    public void delete_Should_CallRepository_When_UserIsAdmin() {
        //Arrange
        User user = createMockAdmin();
        user.setId(2);
        Post post = createMockPost();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        mockService.delete(post.getId(), user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .delete(post.getId());
    }

    @Test
    public void delete_Should_CallRepository_When_UserIsCreator() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        mockService.delete(post.getId(), user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .delete(post.getId());
    }

    @Test
    public void delete_Should_ThrowException_When_UserIsNotCreatorOrAdmin() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();
        user.setId(2);

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> mockService.delete(post.getId(), user));
    }

    @Test
    public void getTop10CommentedPosts_Should_Return_top10CommentedPosts(){
        List<Post> expectedPosts = new ArrayList<>();

        Mockito.when(mockRepository.getTop10MostCommentedPost()).thenReturn(expectedPosts);

        List<Post> result=  mockService.getTop10MostCommentedPosts();

        Assertions.assertEquals(expectedPosts,result);
    }

    @Test
    public void getTop10RecentlyPosts_Should_Return_Top10RecentlyPosts(){
        List<Post> recentlyPosts = new ArrayList<>();

        Mockito.when(mockRepository.get10MostRecentlyCreatedPosts()).thenReturn(recentlyPosts);
        List<Post> result = mockService.get10MostRecentlyCreatedPosts();

        Assertions.assertEquals(recentlyPosts,result);
    }



}
