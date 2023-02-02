package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.annotations.Authorized;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.PostService;

@RestController
@RequestMapping("/post")
// @CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // @Authorized //no need to be logged in to see posts

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(this.postService.getAll(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(this.postService.getAll());
    }

    @Authorized
    @PutMapping
    public ResponseEntity<Post> upsertPost(@RequestBody Post post) {
        return ResponseEntity.ok(this.postService.upsert(post));
    }

    @Authorized
    @PutMapping("/like")
    public ResponseEntity<Post> addOrRemoveLike(@RequestBody Post post, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(this.postService.addOrRemoveLike(post, user));
    }

    @Authorized
    @DeleteMapping("/delete-post")
    public Optional<Post> deletePost(@RequestBody Post post) {
        return this.postService.deletePost(post);
    }
}
