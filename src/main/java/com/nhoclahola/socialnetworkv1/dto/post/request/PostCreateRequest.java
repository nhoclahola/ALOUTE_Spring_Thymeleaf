package com.nhoclahola.socialnetworkv1.dto.post.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostCreateRequest
{
    private String caption;
    private String imageUrl;
    private String videoUrl;
}
