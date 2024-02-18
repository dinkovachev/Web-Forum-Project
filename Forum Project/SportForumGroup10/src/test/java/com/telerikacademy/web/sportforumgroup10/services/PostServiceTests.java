package com.telerikacademy.web.sportforumgroup10.services;
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

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository mockRepository;

    @InjectMocks
    PostService mockService;

    @BeforeEach
    public void setUp() {
        mockService = new PostServiceImpl(mockRepository);
    }

@Test
    void getAll_Should_Call_Repository(){

}



}
