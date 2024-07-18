package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController
{
    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public ApiResponse<CommentResponse> createComment(@PathVariable String postId,
                                                      @RequestBody @Valid CommentCreateRequest request)
    {
        CommentResponse commentResponse = commentService.createComment(postId, request);
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        response.setResult(commentResponse);
        return response;
    }

    @PutMapping("/comments/like/{commentId}")
    public ApiResponse<CommentResponse> likeComment(@PathVariable String commentId)
    {
        CommentResponse commentResponse = commentService.likeComment(commentId);
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        response.setResult(commentResponse);
        return response;
    }
}
