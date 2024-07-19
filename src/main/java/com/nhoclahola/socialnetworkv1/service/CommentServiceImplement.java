package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.entity.Comment;
import com.nhoclahola.socialnetworkv1.entity.Post;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import com.nhoclahola.socialnetworkv1.mapper.CommentMapper;
import com.nhoclahola.socialnetworkv1.repository.CommentRepository;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService
{
    private final PostService postService;
    private final UserService userService;
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
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_EXIST));
    }

    @Override
    @Transactional
    public String likeComment(String commentId)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(userEmail);
        Comment comment = this.findCommentById(commentId);
        if (comment.getLiked().contains(user))
        {
            comment.getLiked().remove(user);
            commentRepository.save(comment);
            return "You just unliked this comment";
        }
        else
        {
            comment.getLiked().add(user);
            commentRepository.save(comment);
            return "You just liked this comment";
        }
    }

    @Override
    public CommentResponse findCommentByIdResponse(String commentId)
    {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_EXIST));
        return commentMapper.toCommentResponse(comment);
    }
}
