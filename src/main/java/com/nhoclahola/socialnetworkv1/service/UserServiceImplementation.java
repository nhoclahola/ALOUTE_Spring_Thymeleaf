package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.configuration.JwtProvider;
import com.nhoclahola.socialnetworkv1.dto.auth.response.AuthResponse;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import com.nhoclahola.socialnetworkv1.mapper.UserMapper;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public AuthResponse createUser(UserCreateRequest request)
    {
        User user = userMapper.userLoginRequestToUser(request);
        // userId is already null after mapping
//        user.setUserId(null);       // To prevent Hibernate from creating a query to check if user is exist or not
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String jwtToken = JwtProvider.generateJwtToken(authentication);
        return new AuthResponse(jwtToken, "Register success");
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
    @PreAuthorize("@userServiceImplementation.isAuthorizedToUpdateUser(#userId) || hasRole('ADMIN')")
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

    @Override
    public UserResponse findUserByEmailResponse(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapper.toUserResponse(user);
    }

    public boolean isAuthorizedToUpdateUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User does not exist");
        }
        String authEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return user.getEmail().equals(authEmail);
    }
}
