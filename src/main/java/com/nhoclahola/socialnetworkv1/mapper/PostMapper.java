package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper
{
    public abstract PostResponse toPostResponse(Post post);

    public abstract List<PostResponse> toListPostResponse(List<Post> posts);

}
