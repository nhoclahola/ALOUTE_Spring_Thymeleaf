package com.nhoclahola.socialnetworkv1.controller.api;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostWithDataResponse;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.service.PostService;
import com.nhoclahola.socialnetworkv1.service.UserService;
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
    private final UserService userService;

    @PostMapping("/posts")
    public ApiResponse<PostResponse> createPost(@RequestPart String caption,
                                          @RequestPart("file") MultipartFile file) throws IOException
    {
        PostResponse result = postService.createNewPost(caption, file);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse<String> deletePost(@PathVariable String postId)
    {
        String message = postService.deletePost(postId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(message);
        return response;
    }

    @GetMapping("/posts/{postId}")
    public ApiResponse<PostWithDataResponse> getPostById(@PathVariable String postId)
    {
        PostWithDataResponse postResponse = postService.findPostWithDataByPostId(postId);
        ApiResponse<PostWithDataResponse> response = new ApiResponse<>();
        response.setResult(postResponse);
        return response;
    }

    @GetMapping("/posts/users/{userId}")
    public ApiResponse<List<PostWithDataResponse>> getUsersPosts(@PathVariable String userId, @RequestParam int index)
    {
        List<PostWithDataResponse> postResponseList = postService.findPostsByUserId(userId, index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
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
    public ApiResponse<List<PostWithDataResponse>> homePagePost(@RequestParam int index)
    {
        List<PostWithDataResponse> postResponseList = postService.getHomeFeeds(index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts/communities")
    public ApiResponse<List<PostWithDataResponse>> communitiesPost(@RequestParam int index)
    {
        List<PostWithDataResponse> postResponseList = postService.getCommunitiesFeeds(index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts/popular_videos")
    public ApiResponse<List<PostWithDataResponse>> getPopularVideo(@RequestParam int index)
    {
        List<PostWithDataResponse> postResponseList = postService.findPopularVideoPosts(index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts/search")
    public ApiResponse<List<PostWithDataResponse>> searchPost(@RequestParam("query") String query, @RequestParam("index") int index)
    {
        List<PostWithDataResponse> postResponseList = postService.searchPost(query, index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts/users/videos/{userId}")
    public ApiResponse<List<PostWithDataResponse>> getUsersVideoPosts(@PathVariable String userId, @RequestParam("index") int index)
    {
        List<PostWithDataResponse> postResponseList = postService.findUsersVideoPosts(userId, index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts/users/saved/{userId}")
    public ApiResponse<List<PostWithDataResponse>> getUsersSavedPosts(@PathVariable String userId, @RequestParam("index") int index)
    {
        List<PostWithDataResponse> postResponseList = postService.findUsersSavedPosts(userId, index);
        ApiResponse<List<PostWithDataResponse>> response = new ApiResponse<>();
        response.setResult(postResponseList);
        return response;
    }

    @GetMapping("/posts/{postId}/users/liked")
    public ApiResponse<List<UserResponse>> getUsersLikedPost(@PathVariable String postId, @RequestParam("index") int index)
    {
        List<UserResponse> users = userService.findUsersLikedPostByPostId(postId, index);
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(users);
        return response;
    }
}
