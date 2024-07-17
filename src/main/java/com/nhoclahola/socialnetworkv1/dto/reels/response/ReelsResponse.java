package com.nhoclahola.socialnetworkv1.dto.reels.response;

import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReelsResponse
{
    private String reelsId;
    private String title;
    private String videoUrl;
    private UserResponse user;
}
