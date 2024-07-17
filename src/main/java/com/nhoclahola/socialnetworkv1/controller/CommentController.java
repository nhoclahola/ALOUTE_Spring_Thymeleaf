package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController
{
    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public CommentResponse createComment(@RequestBody CommentCreateRequest request,
                                         @PathVariable String postId)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.createComment(userEmail, postId, request);
    }

    @PutMapping("/comments/like/{commentId}")
    public CommentResponse likeComment(@PathVariable String commentId)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.likeComment(userEmail, commentId);
    }
}
