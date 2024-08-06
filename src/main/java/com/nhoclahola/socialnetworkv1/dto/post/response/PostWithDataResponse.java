package com.nhoclahola.socialnetworkv1.dto.post.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostWithDataResponse
{
    private String postId;
    private String caption;
    private String imageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    private UserResponse user;
    private long likedCount;
    private long commentCount;
    private boolean isLiked;
}
