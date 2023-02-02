package com.revature.services;

import com.revature.exceptions.NoSuchRecordException;
import com.revature.models.Comment;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.CommentRepository;
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
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock 
    private PostRepository pr;
    @InjectMocks
    private CommentService commentService;

    private Post myPost;

    private User commenter;

    private Comment comment;

    @BeforeEach
    public void setup() {

        comment = Comment.builder()
                .id(1)
                .text("Test String")
                .commenter(commenter)
                .post(myPost)
                .build();
    }

    @Test
    public void CommentService_AddComment_ReturnAddedComment() {

        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        Comment savedComment = commentService.addComment(comment);

        Assertions.assertThat(savedComment).isNotNull();
    }

    @Test
    void CommentService_DeleteComment_ReturnDeletedComment() throws NoSuchRecordException {

        when(commentRepository.findById(Mockito.eq(1))).thenReturn(Optional.ofNullable(comment));

        willDoNothing().given(commentRepository).deleteById(comment.getId());


        commentService.deleteComment(comment);


        verify(commentRepository, times(1)).deleteById(comment.getId());
    }

    @Test
    void CommentService_GetAll_ReturnAllComments() {

        List<Comment> mockList = new ArrayList<>();
        Post post = new Post();
        Comment mockUser1 = Mockito.mock(Comment.class);
        Comment mockUser2 = Mockito.mock(Comment.class);

        mockList.add(mockUser1);
        mockList.add(mockUser2);

        post.setPostId(1);
        post.setComments(mockList);

        when(pr.findById(post.getPostId())).thenReturn(Optional.of(post));

        // Assert
        Assertions.assertThat(commentService.getAll(post)).hasSize(2);

    }
}
