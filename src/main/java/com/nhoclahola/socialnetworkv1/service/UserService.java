package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.auth.response.AuthResponse;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.User;

import java.util.List;

public interface UserService
{
    public abstract List<UserResponse> findAllUsers();

    public abstract AuthResponse createUser(UserCreateRequest request);

    public abstract User findUserById(String userId);

    public abstract User findUserByEmail(String email);

    public abstract UserResponse followUser(String userIdToFollow);

    public abstract UserResponse updateUser(String userId, UserUpdateRequest request);

    public abstract List<UserResponse> searchUser(String query);

    public abstract UserResponse getUserFromToken();

    public abstract UserResponse findUserByIdResponse(String userId);

    public abstract UserResponse findUserByEmailResponse(String email);
}
