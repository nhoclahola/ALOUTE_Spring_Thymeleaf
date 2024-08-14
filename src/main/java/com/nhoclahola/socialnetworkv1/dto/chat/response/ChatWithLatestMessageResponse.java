package com.nhoclahola.socialnetworkv1.dto.chat.response;

import com.nhoclahola.socialnetworkv1.dto.message.response.MessageResponse;
import com.nhoclahola.socialnetworkv1.dto.user.response.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatWithLatestMessageResponse
{
    private String chatId;
    private String chatName;
    private String chatImageUrl;
    private LocalDateTime timestamp;
    private List<UserResponse> users;
    private MessageResponse latestMessage;
}
