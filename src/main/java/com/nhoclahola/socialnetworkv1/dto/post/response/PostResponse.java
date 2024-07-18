package com.nhoclahola.socialnetworkv1.dto.post.response;

import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostResponse
{
    private String postId;
    private String caption;
    private String imageUrl;
    private String videoUrl;
    private LocalDateTime createdAt;
    private List<CommentResponse> comments;
}
