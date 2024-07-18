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
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request)
    {
        UserResponse userResponse = userService.updateUser(id, request);
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userResponse);
        return response;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id)
    {
        return "Deleted user successfully";
    }

    @PutMapping("/users/follow/{userIdToFollow}")
    public UserResponse followUserHandler(@PathVariable String userIdToFollow)
    {
        return userService.followUser(userIdToFollow);
    }

    @GetMapping("users/search")
    public ApiResponse<List<UserResponse>> searchUser(@RequestParam("query") String query)
    {
        List<UserResponse> userResponseList = userService.searchUser(query);
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userResponseList);
        return response;
    }

    @GetMapping("users/fromToken")
    public ApiResponse<UserResponse> getUserFromToken()
    {
        UserResponse userResponse = userService.getUserFromToken();
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userResponse);
        return response;
    }
}
