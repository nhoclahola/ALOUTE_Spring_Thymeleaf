package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController
{
    private final CommentService commentService;

    @PostMapping("/comments/posts/{postId}")
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

    @GetMapping("/comments/posts/{postId}")
    public ApiResponse<List<CommentResponse>> getPostsComments(@PathVariable String postId, @RequestParam int index)
    {
        List<CommentResponse> commentResponseList = commentService.findCommentsByPostId(postId, index);
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();
        response.setResult(commentResponseList);
        return response;
    }
}
