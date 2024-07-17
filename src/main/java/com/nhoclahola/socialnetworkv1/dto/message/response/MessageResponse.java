package com.nhoclahola.socialnetworkv1.dto.message.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse
{
    private String messageId;
    private String content;
    private String imageUrl;
    private LocalDateTime timeStamp;
}
