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

    @PostMapping("/comments/post/{postId}")
    public ApiResponse<CommentResponse> createComment(@PathVariable String postId,
                                                      @RequestBody @Valid CommentCreateRequest request)
    {
        CommentResponse commentResponse = commentService.createComment(postId, request);
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        response.setResult(commentResponse);
        return response;
    }

    @PutMapping("/comments/like/{commentId}")
    public ApiResponse<String> likeComment(@PathVariable String commentId)
    {
        String result = commentService.likeComment(commentId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    public ApiResponse<CommentResponse> getCommentById(@PathVariable String commentId)
    {
        CommentResponse commentResponse = commentService.findCommentByIdResponse(commentId);
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        response.setResult(commentResponse);
        return response;
    }
}
