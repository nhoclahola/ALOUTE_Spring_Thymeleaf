package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.post.PostWithData;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostWithDataResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PostMapper
{
    public abstract PostResponse toPostResponse(Post post);

    public abstract List<PostResponse> toListPostResponse(List<Post> posts);

    public abstract List<PostResponse> toListPostResponse(Set<Post> posts);

    // Because Lombok will build to getter "isLiked", not "getIsLiked" so Mapstruct will get it wrong

    @Mapping(source = "liked", target = "isLiked")

    public abstract PostWithDataResponse toPostWithDataResponse (PostWithData postWithData);

    public abstract List<PostWithDataResponse> toListPostWithDataResponse(List<PostWithData> postWithDataList);

    public abstract List<PostWithDataResponse> toListPostWithDataResponse(Set<PostWithData> postWithDataSet);

}
