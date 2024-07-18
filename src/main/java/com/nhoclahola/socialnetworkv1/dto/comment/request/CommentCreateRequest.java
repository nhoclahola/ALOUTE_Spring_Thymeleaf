package com.nhoclahola.socialnetworkv1.dto.comment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentCreateRequest
{
    @NotBlank(message = "Comment can not be blank")
    private String content;
}
