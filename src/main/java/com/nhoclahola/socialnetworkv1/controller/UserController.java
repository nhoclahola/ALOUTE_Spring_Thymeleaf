package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController
{
    private final UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<UserResponse>> getAllUsers()
    {
        List<UserResponse> userResponses = userService.findAllUsers();
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userResponses);
        return response;
    }

    @GetMapping("/users/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id)
    {
        UserResponse userResponse = userService.findUserByIdResponse(id);
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userResponse);
        return response;
    }

    @PutMapping("/users/{id}")
    public ApiResponse<String> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request)
    {
        String result = userService.updateUser(id, request);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id)
    {
        return "Deleted user successfully";
    }

    @PostMapping("/users/follow/{userIdToFollow}")
    public ApiResponse<String> followUser(@PathVariable String userIdToFollow)
    {
        String result = userService.followUser(userIdToFollow);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @GetMapping("/users/search")
    public ApiResponse<List<UserResponse>> searchUser(@RequestParam("query") String query)
    {
        List<UserResponse> userResponseList = userService.searchUser(query);
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userResponseList);
        return response;
    }

    @GetMapping("/users/fromToken")
    public ApiResponse<UserResponse> getUserFromToken()
    {
        UserResponse userResponse = userService.getUserFromToken();
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userResponse);
        return response;
    }

    @GetMapping("/users/followings/{userId}")
    public ApiResponse<List<UserResponse>> getUsersFollowings(@PathVariable String userId)
    {

        List<UserResponse> userResponseList = userService.findUsersFollowing(userId);
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userResponseList);
        return response;
    }

    @GetMapping("/users/followers/{userId}")
    public ApiResponse<List<UserResponse>> getUsersFollowers(@PathVariable String userId)
    {

        List<UserResponse> userResponseList = userService.findUsersFollower(userId);
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userResponseList);
        return response;
    }
}
