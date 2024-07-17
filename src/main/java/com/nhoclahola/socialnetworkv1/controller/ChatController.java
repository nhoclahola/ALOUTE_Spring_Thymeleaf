package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.chat.response.ChatResponse;
import com.nhoclahola.socialnetworkv1.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController
{
    private final ChatService chatService;

    @PostMapping("/chats/users/{targetUserId}")
    public ChatResponse createChat(@PathVariable String targetUserId)
    {
        return chatService.createChat(targetUserId);
    }

    @GetMapping("/chats/users/{userId}")
    public List<ChatResponse> getUsersChat(@PathVariable String userId)
    {
        return chatService.findUsersChat(userId);
    }

    @GetMapping("/chats/{chatId}")
    public ChatResponse getChatById(@PathVariable String chatId)
    {
        return chatService.findChatByIdResponse(chatId);
    }
}
