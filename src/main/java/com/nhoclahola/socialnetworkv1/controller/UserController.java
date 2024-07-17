package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController
{
    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getUsers()
    {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable String id)
    {
        return userService.findUserByIdResponse(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request)
    {
        return userService.updateUser(id, request);
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
    public List<UserResponse> searchUser(@RequestParam("query") String query)
    {
        return userService.searchUser(query);
    }

    @GetMapping("users/fromToken")
    public UserResponse getUserFromToken()
    {
        return userService.getUserFromToken();
    }
}
