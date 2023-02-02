package com.revature.services;

import com.revature.models.Followers;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.FollowersRepository;
import com.revature.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

        @Mock
        private PostRepository postRepository;
        @Mock
        private FollowersRepository fr;
        @InjectMocks
        private PostService postService;
        private Post post;
        private User user;

        private Followers follower;

        @BeforeEach
        public void setup() {

                post = Post.builder()
                                .postId(1)
                                .text("Test String")
                                .author(user)
                                .build();
        }

    @Test
    public void PostService_UpsertPost_ReturnUpsertPost() {

        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        Post savedPost = postService.upsert(post);

        Assertions.assertThat(savedPost).isNotNull();
    }

        @Test
        void PostService_DeletePost_ReturnDeletedPost() {

            int postId = 1;


            willDoNothing().given(postRepository).delete(post);


            postService.deletePost(post);

            verify(postRepository, times(1)).delete(post);
        }

    @Test
    void PostService_GetAll_ReturnAllPosts() {
        User user1 = User.builder().build();

        post.setAuthor(user);
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Followers ft = new Followers();
        List<User> following = new ArrayList<>();
        following.add(user);
        ft.setFollowing(following);

        when(fr.existsByUser(user1)).thenReturn(false);
        when(postRepository.findAllByAuthor(user1)).thenReturn(posts);

        Assertions.assertThat(postService.getAll(user1)).hasSize(1);
    }

        @Test
        void PostService_AddLike() {
                User user = User.builder()
                                .id(1)
                                .email("test@test.com")
                                .password("password").build();

                List<User> likes = new ArrayList<>();

                post = Post.builder()
                                .postId(1)
                                .text("Test String")
                                .author(user)
                                .likes(likes)
                                .build();

                when(postRepository.save(post)).thenReturn(post);

                Post savedPost = postService.addOrRemoveLike(post, user);

                Assertions.assertThat(savedPost).isNotNull();
                Assertions.assertThat(savedPost.getLikes().size()).isEqualTo(1);
        }

        @Test
        void PostService_RemoveLike() {
                User user = User.builder()
                                .id(1)
                                .email("test@test.com")
                                .password("password").build();

                List<User> likes = new ArrayList<>();
                likes.add(user);

                post = Post.builder()
                                .postId(1)
                                .text("Test String")
                                .author(user)
                                .likes(likes)
                                .build();

                when(postRepository.save(post)).thenReturn(post);

                Post savedPost = postService.addOrRemoveLike(post, user);

                Assertions.assertThat(savedPost).isNotNull();
                Assertions.assertThat(savedPost.getLikes().size()).isEqualTo(0);
        }

}