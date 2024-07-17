package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper
{
    public abstract Comment commentCreateRequestToComment(CommentCreateRequest request);

    public abstract CommentResponse toCommentResponse(Comment comment);
}
