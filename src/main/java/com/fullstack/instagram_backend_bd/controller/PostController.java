package com.fullstack.instagram_backend_bd.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.instagram_backend_bd.api.request.PostCreateRequest;
import com.fullstack.instagram_backend_bd.model.Post;
import com.fullstack.instagram_backend_bd.service.post.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest postRequest) {
        logger.info("Creating a new post with request: {}", postRequest);
        Post savedPost = postService.savePost(postRequest);
        logger.info("Post created successfully. Post ID: {}", savedPost.getId());
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        logger.info("Getting post by id: {}", id);
        Optional<Post> post = postService.findPostById(id);
        if (post.isPresent()) {
            logger.info("Post found: id={}, caption={}", post.get().getId(), post.get().getCaption());
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } else {
            logger.info("Post not found by id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile-info/{profileInfoId}")
    public ResponseEntity<List<Post>> getPostsByProfileInfoId(@PathVariable Long profileInfoId) {
        logger.info("Getting all posts by profile info id: {}", profileInfoId);
        List<Post> posts = postService.findPostsByProfileInfoId(profileInfoId);
        logger.info("Posts found: {}", posts.size());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        logger.info("Getting all posts");
        List<Post> posts = postService.findAllPosts();
        logger.info("Posts found: {}", posts.size());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        logger.info("Deleting post by id: {}", id);
        postService.deletePostById(id);
        logger.info("Post deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
