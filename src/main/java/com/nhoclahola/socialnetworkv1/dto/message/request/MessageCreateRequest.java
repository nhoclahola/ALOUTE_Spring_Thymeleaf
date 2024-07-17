package com.nhoclahola.socialnetworkv1.dto.message.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateRequest
{
    private String content;
    private String imageUrl;
}
