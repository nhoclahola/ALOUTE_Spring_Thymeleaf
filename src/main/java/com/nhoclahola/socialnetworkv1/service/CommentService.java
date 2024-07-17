package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.entity.Comment;

public interface CommentService
{
    public CommentResponse createComment(String userEmail, String postId, CommentCreateRequest commentCreateRequest);

    public Comment findCommentById(String commentId);

    public CommentResponse likeComment(String userEmail, String commentId);


}
