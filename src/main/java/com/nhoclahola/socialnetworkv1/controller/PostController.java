package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.post.request.PostCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController
{
    private final PostService postService;

    @PostMapping("/posts")
    public ApiResponse<String> createPost(@RequestBody @Valid PostCreateRequest request)
    {
        String result = postService.createNewPost(request);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable String postId)
    {
        String message = postService.deletePost(postId);
        return message;
    }

    @GetMapping("/posts/{postId}")
    public ApiResponse<PostResponse> getPostById(@PathVariable String postId)
    {
        PostResponse postResponse = postService.findPostByIdResponse(postId);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        response.setResult(postResponse);
        return response;
    }

    @GetMapping("/posts/user/{userId}")
    public ApiResponse<List<PostResponse>> getUsersPosts(@PathVariable String userId)
    {
        List<PostResponse> postResponseList = postService.findPostByUserId(userId);
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts")
    public ApiResponse<List<PostResponse>> getAllPosts()
    {
        List<PostResponse> postResponseList = postService.findAllPosts();
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @PutMapping("/posts/save/{postId}")
    public ApiResponse<String> savePost(@PathVariable String postId)
    {
        String result = postService.savePost(postId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @PutMapping("/posts/like/{postId}")
    public ApiResponse<String> likePost(@PathVariable String postId)
    {
        String result = postService.likePost(postId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }
}
