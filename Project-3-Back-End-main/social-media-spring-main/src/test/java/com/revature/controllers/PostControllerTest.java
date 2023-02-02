package com.revature.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.services.PostService;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTest {

    @MockBean
    private PostService postService;

    @Test
    void testAddOrRemoveLike() {

    }

    @Test
    void testDeletePost() {

    }

    @Test
    void testGetAllPosts() {

    }

    @Test
    void testUpsertPost() {

    }
}
