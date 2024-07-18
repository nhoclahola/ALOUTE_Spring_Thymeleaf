package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.auth.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.Role;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import com.nhoclahola.socialnetworkv1.mapper.UserMapper;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAllUsers()
    {
        List<User> users = userRepository.findAll();
        return userMapper.toListUserResponse(users);
    }

    @Override
    @Transactional
    public User createUser(UserCreateRequest request)
    {
        User user = userMapper.userLoginRequestToUser(request);
        // userId is already null after mapping
//        user.setUserId(null);       // To prevent Hibernate from creating a query to check if user is exist or not
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(String userId)
    {
        return userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    @Transactional
    public UserResponse followUser(String userIdToFollow)
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = this.findUserByEmail(currentUserEmail);
        User userToFollow = userRepository.findById(userIdToFollow).orElseThrow(() ->
                new RuntimeException("User " + userIdToFollow + " is not exist"));

        if (!currentUser.getFollowings().contains(userIdToFollow))
        {
            currentUser.getFollowings().add(userIdToFollow);
            userToFollow.getFollowers().add(currentUser.getUserId());
            userRepository.save(currentUser);
            userRepository.save(userToFollow);
        }
        return userMapper.toUserResponse(currentUser);
    }

    @Override
    @Transactional
    public UserResponse updateUser(String userId, UserUpdateRequest request)
    {
        User oldUser = userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
        userMapper.updateUser(oldUser, request);
        userRepository.save(oldUser);
        return userMapper.toUserResponse(oldUser);
    }

    @Override
    public List<UserResponse> searchUser(String query)
    {
        List<User> users = userRepository.searchUser(query);
        return userMapper.toListUserResponse(users);
    }

    @Override
    public UserResponse getUserFromToken()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.findUserByEmail(email);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse findUserByIdResponse(String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapper.toUserResponse(user);
    }
}
