package com.nhoclahola.socialnetworkv1.dto.reels.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReelsCreateRequest
{
    private String title;
    private String videoUrl;
}
