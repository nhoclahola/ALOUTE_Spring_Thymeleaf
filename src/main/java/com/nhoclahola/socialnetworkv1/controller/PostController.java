package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostWithDataResponse;
import com.nhoclahola.socialnetworkv1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController
{
    private final PostService postService;

    @PostMapping("/posts")
    public ApiResponse<PostResponse> createPost(@RequestPart String caption,
                                          @RequestPart("image") MultipartFile image,
                                          @RequestPart("video") MultipartFile video) throws IOException
    {
        PostResponse result = postService.createNewPost(caption, image, video);
        ApiResponse<PostResponse> response = new ApiResponse<>();
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

    @PutMapping("/posts/{postId}/save")
    public ApiResponse<String> savePost(@PathVariable String postId)
    {
        String result = postService.savePost(postId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @PutMapping("/posts/{postId}/like")
    public ApiResponse<String> likePost(@PathVariable String postId)
    {
        String result = postService.likePost(postId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @GetMapping("/posts/homepage")
    public ApiResponse<List<PostWithDataResponse>> homePagePost(@RequestParam int followingIndex, @RequestParam int randomIndex)
    {
        List<PostWithDataResponse> postResponseList = postService.getHomeFeed(followingIndex, randomIndex);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }
}
