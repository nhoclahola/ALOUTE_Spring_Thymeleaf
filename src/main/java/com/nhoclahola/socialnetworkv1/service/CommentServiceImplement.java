package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.entity.Comment;
import com.nhoclahola.socialnetworkv1.entity.Post;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.mapper.CommentMapper;
import com.nhoclahola.socialnetworkv1.repository.CommentRepository;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService
{
    private final PostService postService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentResponse createComment(String postId, CommentCreateRequest commentCreateRequest)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(userEmail);
        Post post = postService.findPostById(postId);
        Comment comment = commentMapper.commentCreateRequestToComment(commentCreateRequest);
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }

    @Override
    public Comment findCommentById(String commentId)
    {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment does not exist"));
        return comment;
    }

    @Override
    public CommentResponse likeComment(String commentId)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(userEmail);
        Comment comment = this.findCommentById(commentId);
        if (!user.getLikedComments().contains(comment))
            user.getLikedComments().add(comment);
        else
            user.getLikedComments().remove(comment);
        userRepository.save(user);
        return commentMapper.toCommentResponse(comment);
    }
}
