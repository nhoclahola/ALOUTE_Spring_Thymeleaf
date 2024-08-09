package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.post.PostWithData;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostWithDataResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = UserMapper.class) // To change avatarUrl with method in UserMapper
public interface PostMapper
{
    @Mapping(target = "imageUrl", expression = "java(post.getImageUrl() != null ? com.nhoclahola.socialnetworkv1.configuration.WebConfig.serverAdress + post.getImageUrl() : null)")
    @Mapping(target = "videoUrl", expression = "java(post.getVideoUrl() != null ? com.nhoclahola.socialnetworkv1.configuration.WebConfig.serverAdress + post.getVideoUrl() : null)")
    public abstract PostResponse toPostResponse(Post post);

    public abstract List<PostResponse> toListPostResponse(List<Post> posts);

    public abstract List<PostResponse> toListPostResponse(Set<Post> posts);

    // Because Lombok will build to getter "isLiked", not "getIsLiked" so Mapstruct will get it wrong

    @Mapping(source = "liked", target = "isLiked")
    @Mapping(target = "imageUrl", expression = "java(postWithData.getImageUrl() != null ? com.nhoclahola.socialnetworkv1.configuration.WebConfig.serverAdress + postWithData.getImageUrl() : null)")
    @Mapping(target = "videoUrl", expression = "java(postWithData.getVideoUrl() != null ? com.nhoclahola.socialnetworkv1.configuration.WebConfig.serverAdress + postWithData.getVideoUrl() : null)")
    public abstract PostWithDataResponse toPostWithDataResponse (PostWithData postWithData);

    public abstract List<PostWithDataResponse> toListPostWithDataResponse(List<PostWithData> postWithDataList);

    public abstract List<PostWithDataResponse> toListPostWithDataResponse(Set<PostWithData> postWithDataSet);

}
