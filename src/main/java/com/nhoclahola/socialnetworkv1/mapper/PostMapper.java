package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.post.PostWithLikes;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostWithLikesResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PostMapper
{
    public abstract PostResponse toPostResponse(Post post);

    public abstract List<PostResponse> toListPostResponse(List<Post> posts);

    public abstract List<PostResponse> toListPostResponse(Set<Post> posts);

    public abstract List<PostWithLikesResponse> toListPostWithLikesResponse(List<PostWithLikes> postWithLikesList);

    public abstract List<PostWithLikesResponse> toListPostWithLikesResponse(Set<PostWithLikes> postWithLikesSet);

}
