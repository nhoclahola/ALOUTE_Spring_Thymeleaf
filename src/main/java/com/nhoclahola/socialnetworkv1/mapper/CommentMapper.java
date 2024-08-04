package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.comment.request.CommentCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import com.nhoclahola.socialnetworkv1.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper
{
    public abstract Comment commentCreateRequestToComment(CommentCreateRequest request);

    public abstract CommentResponse toCommentResponse(Comment comment);

    public abstract List<CommentResponse> toListCommentResponse(List<Comment> comments);
}
