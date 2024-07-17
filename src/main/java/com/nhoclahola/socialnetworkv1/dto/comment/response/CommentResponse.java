package com.nhoclahola.socialnetworkv1.dto.comment.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentResponse
{
    private String commentId;
    private String content;
    private LocalDateTime createdAt;
}
