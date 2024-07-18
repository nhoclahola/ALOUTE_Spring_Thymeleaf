package com.nhoclahola.socialnetworkv1.dto.reels.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReelsCreateRequest
{
    @NotBlank(message = "Title can not be blank")
    private String title;
    @NotBlank(message = "Video can not be empty")
    private String videoUrl;
}
