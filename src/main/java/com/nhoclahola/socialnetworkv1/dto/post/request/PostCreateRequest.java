package com.nhoclahola.socialnetworkv1.dto.post.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/***
 * Not used
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostCreateRequest
{
    @NotBlank(message = "Caption can not be blank")
    private String caption;
    private String imageUrl;
}
