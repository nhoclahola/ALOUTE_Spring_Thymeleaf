package com.nhoclahola.socialnetworkv1.dto.message.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateRequest
{
    @NotBlank(message = "Message can not be blank")
    private String content;
    private String imageUrl;
}
