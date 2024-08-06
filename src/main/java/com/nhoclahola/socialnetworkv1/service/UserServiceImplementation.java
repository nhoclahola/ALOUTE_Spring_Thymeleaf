package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.auth.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.UserWithData;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ImageUploadServiceImplementation imageUploadServiceImplementation;

    private final String AVATAR_DIR = "/avatar/";
    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> findAllUsers()
    {
        List<User> users = userRepository.findAll();
        return userMapper.toListUserResponse(users);
    }

    @Override
    @Transactional
    public User createUser(UserCreateRequest request)
    {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXIST_REGISTER);
        User user = userMapper.userLoginRequestToUser(request);
        // userId is already null after mapping,
        // Just don't set it, Hibernate won't check it is exist or not again
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
    public UserResponse findUserByIdResponse(String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserWithData findUserDataByUserId(String userId)
    {
        return userRepository.findUserWithDataByUserId(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    @Transactional
    public String followUser(String userIdToFollow)
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = this.findUserByEmail(currentUserEmail);
        if (currentUser.getUserId().equals(userIdToFollow))
            throw new AppException(ErrorCode.FOLLOW_YOURSELF);
        User userToFollow = userRepository.findById(userIdToFollow).orElseThrow(() ->
                new RuntimeException("User " + userIdToFollow + " is not exist"));
        if (!currentUser.getFollowings().contains(userToFollow))
        {
            currentUser.getFollowings().add(userToFollow);
            userRepository.save(currentUser);
            return "You just followed " + userToFollow.getEmail();
        }
        else
        {
            currentUser.getFollowings().remove(userToFollow);
            userRepository.save(currentUser);
            return "You just unfollowed " + userToFollow.getEmail();
        }
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
    public UserResponse findUserFromToken()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.findUserByEmail(email);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUserFromToken(UserUpdateRequest request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.findUserByEmail(email);
        userMapper.updateUser(user, request);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> findUsersFollowing(String userId)
    {
        List<User> usersFollowing = userRepository.findUsersFollowings(userId);
        return userMapper.toListUserResponse(usersFollowing);
    }

    @Override
    public List<UserResponse> findUsersFollower(String userId)
    {
        List<User> usersFollowers = userRepository.findUsersFollowers(userId);
        return userMapper.toListUserResponse(usersFollowers);
    }

    @Override
    public UserResponse uploadAvatar(MultipartFile image) throws IOException
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = this.findUserByEmail(currentUserEmail);
        String uploadAvatarDir = AVATAR_DIR + currentUser.getUserId() + "/";
        String avatarUrl = imageUploadServiceImplementation.upload(uploadAvatarDir, image);
        currentUser.setAvatarUrl(avatarUrl);
        userRepository.save(currentUser);
        return userMapper.toUserResponse(currentUser);
    }


}
