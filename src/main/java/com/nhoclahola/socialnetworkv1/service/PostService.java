package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.post.request.PostCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService
{
    public abstract String createNewPost(String caption, MultipartFile image, MultipartFile video) throws IOException;

    public abstract String deletePost(String postId);

    public abstract List<PostResponse> findPostByUserId(String userId);

    public abstract Post findPostById(String postId);

    public abstract List<PostResponse> findAllPosts();

    public abstract String savePost(String postId);

    public abstract String likePost(String postId);

    public abstract PostResponse findPostByIdResponse(String postId);

    public abstract List<PostResponse> getHomeFeed(int followedPostIndex, int randomPostIndex);

}
