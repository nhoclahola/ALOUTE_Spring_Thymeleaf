package com.nhoclahola.socialnetworkv1.dto.comment.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentCreateRequest
{
    private String content;
}
