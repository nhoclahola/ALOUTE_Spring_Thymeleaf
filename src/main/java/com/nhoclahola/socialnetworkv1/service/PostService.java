package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.post.request.PostCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;

import java.util.List;

public interface PostService
{
    public abstract String createNewPost(PostCreateRequest post);

    public abstract String deletePost(String postId);

    public abstract List<PostResponse> findPostByUserId(String userId);

    public abstract Post findPostById(String postId);

    public abstract List<PostResponse> findAllPosts();

    public abstract String savePost(String postId);

    public abstract String likePost(String postId);

    public abstract PostResponse findPostByIdResponse(String postId);


}
