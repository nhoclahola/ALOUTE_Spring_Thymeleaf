package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.post.request.PostCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.post.response.PostResponse;
import com.nhoclahola.socialnetworkv1.entity.Post;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.mapper.PostMapper;
import com.nhoclahola.socialnetworkv1.repository.PostRepository;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostResponse createNewPost(PostCreateRequest post)
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Post newPost = Post.builder()
                .caption(post.getCaption())
                .imageUrl(post.getImageUrl())
                .videoUrl(post.getVideoUrl())
                .user(currentUser)
                .createdAt(LocalDateTime.now())
                .build();
        postRepository.save(newPost);
        return postMapper.toPostResponse(newPost);
    }

    @Override
    public String deletePost(String postId)
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new RuntimeException("Post is not exist"));
        if (post.getUser() != currentUser)
            throw new RuntimeException("You can't delete this post");
        postRepository.delete(post);
        return "Post have been deleted successful";
    }

    @Override
    public List<PostResponse> findPostByUserId(String userId)
    {
        List<Post> posts = postRepository.findPostByUserId(userId);
        return postMapper.toListPostResponse(posts);
    }

    @Override
    public Post findPostById(String postId)
    {
        return postRepository.findById(postId).orElseThrow(() ->
                new RuntimeException("Post is not exist"));
    }

    @Override
    public List<PostResponse> findAllPosts()
    {
        List<Post> posts = postRepository.findAll();
        return postMapper.toListPostResponse(posts);
    }

    @Override
    public PostResponse savePost(String postId)
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Post post = this.findPostById(postId);
        if (currentUser.getSavedPost().contains(post))
            currentUser.getSavedPost().remove(post);
        else
            currentUser.getSavedPost().add(post);
        userRepository.save(currentUser);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse likePost(String postId)
    {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUserByEmail(currentUserEmail);
        Post post = this.findPostById(postId);
        if (currentUser.getLikedPost().contains(post))
            currentUser.getLikedPost().remove(post);
        else
            currentUser.getLikedPost().add(post);
        userRepository.save(currentUser);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse findPostByIdResponse(String postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new RuntimeException("Post is not exist"));
        return postMapper.toPostResponse(post);
    }
}
