package com.revature.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.revature.models.Post;
import com.revature.models.User;

// @DataJpaTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
// public class PostRepositoryTest {
//     @Autowired
//     private PostRepository postRepo;

//     @Autowired
//     private UserRepository userRepo;

//     @Test
//     public void PostRepository_SaveAll_ReturnsSavedPosts() {

//         User user = User.builder().email("test@test.com").password("password").build();

//         userRepo.save(user);

//         Post post = Post.builder().text("text").author(user).build();
//         Post savedPost = postRepo.save(post);

//         Assertions.assertThat(savedPost).isNotNull();
//         Assertions.assertThat(savedPost.getPostId()).isGreaterThan(0);
//     }

//     @Test
//     public void PostRepository_GetAll_ReturnsSavedMoreThanOnePost() {

//         User user = User.builder().email("test@test.com").password("password").build();
//         Post post = Post.builder().text("text").author(user).build();
//         Post post2 = Post.builder().text("text2").author(user).build();

//         userRepo.save(user);

//         postRepo.save(post);
//         postRepo.save(post2);

//         List<Post> postList = postRepo.findAll();

//         Assertions.assertThat(postList).isNotNull();
//         Assertions.assertThat(postList.size()).isEqualTo(14);
//     }

//     @Test
//     public void PostRepository_FindById_ReturnsSavedPost() {

//         User user = User.builder().email("test@test.com").password("password").build();

//         userRepo.save(user);

//         Post post = Post.builder().text("text").author(user).build();
//         postRepo.save(post);

//         Post returnedPost = postRepo.findById(post.getPostId()).get();

//         Assertions.assertThat(returnedPost).isNotNull();
//     }

//     @Test
//     public void PostRepository_UpdatePost_ReturnsPost() {

//         User user = User.builder().email("test@test.com").password("password").build();

//         userRepo.save(user);
//         Post post = Post.builder().text("text").author(user).build();
//         postRepo.save(post);
//         Post returnedPost = postRepo.findById(post.getPostId()).get();
//         returnedPost.setText("new text");
//         Post updatedPost = postRepo.save(returnedPost);

//         Assertions.assertThat(updatedPost.getText()).isNotNull();
//     }
// }
