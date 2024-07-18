package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.message.request.MessageCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.message.response.MessageResponse;
import com.nhoclahola.socialnetworkv1.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController
{
    private final MessageService messageService;

    @PostMapping("/messages/chats/{chatId}")
    public MessageResponse createMessage(@PathVariable String chatId,
                                         @RequestBody @Valid MessageCreateRequest request)
    {
        return messageService.createMessage(chatId, request);
    }

    @GetMapping("messages/chats/{chatId}")
    public List<MessageResponse> getChatsMessages(@PathVariable String chatId)
    {
        return messageService.findMessagesByChatId(chatId);
    }

    @GetMapping("/messages/{messageId}")
    public MessageResponse getMessageById(@PathVariable String messageId)
    {
        return messageService.findMessageByIdResponse(messageId);
    }
}
