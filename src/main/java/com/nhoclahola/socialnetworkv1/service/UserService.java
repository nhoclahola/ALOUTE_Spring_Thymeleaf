package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.auth.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.User;

import java.util.List;

public interface UserService
{
    public abstract List<UserResponse> findAllUsers();

    public abstract User createUser(UserCreateRequest request);

    public abstract User findUserById(String userId);

    public abstract User findUserByEmail(String email);

    public abstract String followUser(String userIdToFollow);

    public abstract UserResponse updateUser(String userId, UserUpdateRequest request);

    public abstract List<UserResponse> searchUser(String query);

    public abstract UserResponse getUserFromToken();

    public abstract UserResponse updateUserFromToken(UserUpdateRequest request);

    public abstract UserResponse findUserByIdResponse(String userId);

    public abstract List<UserResponse> findUsersFollowing(String userId);

    public abstract List<UserResponse> findUsersFollower(String userId);
}
