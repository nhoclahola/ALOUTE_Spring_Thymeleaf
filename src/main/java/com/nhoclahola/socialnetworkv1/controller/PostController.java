package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.auth.response.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.post.request.PostCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import com.nhoclahola.socialnetworkv1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController
{
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request)
    {
        PostResponse createdPost = postService.createNewPost(request);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable String postId)
    {
        String message = postService.deletePost(postId);
        ApiResponse response = ApiResponse.builder()
                .message(message)
                .status(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable String postId)
    {
        PostResponse post = postService.findPostByIdResponse(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(post);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<PostResponse>> findUsersPosts(@PathVariable String userId)
    {
        List<PostResponse> posts = postService.findPostByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(posts);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> findAllPosts()
    {
        List<PostResponse> posts = postService.findAllPosts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(posts);
    }

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<PostResponse> savePost(@PathVariable String postId)
    {
        PostResponse post = postService.savePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(post);
    }

    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<PostResponse> likePost(@PathVariable String postId)
    {
        PostResponse post = postService.likePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(post);
    }
}
