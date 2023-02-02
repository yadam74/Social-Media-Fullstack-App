package com.revature.services;

import com.revature.exceptions.NoSuchRecordException;
import com.revature.models.Comment;
import com.revature.models.Post;
import com.revature.repositories.CommentRepository;
import com.revature.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    public CommentRepository commentRepository;

    @Autowired
    public PostRepository postRepository;

    public List<Comment> getAll(Post post) {



        Optional<Post> post1 =this.postRepository.findById(post.getPostId());

        return post1.get().getComments();
    }

    // public List<Comment> getAll(Post post) {
    //     return this.commentRepository.findAllByPost(post);
    // }

    public Comment addComment(Comment comment) {
        System.out.println("id:-------------" + comment.getText()
                + "----------------------------------------------------------------");
        return commentRepository.save(comment);
    }

    public Comment deleteComment(Comment comment) throws NoSuchRecordException {
        Optional<Comment> deletedComment = this.commentRepository.findById(comment.getId());
        if (!deletedComment.isEmpty()) {
            Comment deleted = deletedComment.get();
            this.commentRepository.deleteById(comment.getId());
            return deleted;
        } else {
            throw new NoSuchRecordException("Comment not found");
        }
    }
}
