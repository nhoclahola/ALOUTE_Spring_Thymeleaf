package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.user.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.User;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    public abstract User userLoginRequestToUser(UserCreateRequest request);

    public abstract UserResponse toUserResponse(User user);

    public abstract List<UserResponse> toListUserResponse(List<User> users);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
