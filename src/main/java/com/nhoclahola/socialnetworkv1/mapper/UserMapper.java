package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.auth.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserUpdateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import com.nhoclahola.socialnetworkv1.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    public abstract User userLoginRequestToUser(UserCreateRequest request);

    @Mapping(target = "avatarUrl", expression = "java(user.getAvatarUrl() != null ? com.nhoclahola.socialnetworkv1.configuration.WebConfig.serverAdress + user.getAvatarUrl() : null)")
    public abstract UserResponse toUserResponse(User user);

    public abstract List<UserResponse> toListUserResponse(List<User> users);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
