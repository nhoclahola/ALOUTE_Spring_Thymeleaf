package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.message.request.MessageCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.message.response.MessageResponse;
import com.nhoclahola.socialnetworkv1.entity.Chat;
import com.nhoclahola.socialnetworkv1.entity.Message;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import com.nhoclahola.socialnetworkv1.mapper.MessageMapper;
import com.nhoclahola.socialnetworkv1.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImplementation implements MessageService
{
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;
    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public MessageResponse createMessage(String chatId, MessageCreateRequest request)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(userEmail);
        Chat chat = chatService.findChatById(chatId);
        Message message = messageMapper.messageCreateRequestToMessage(request);
        message.setUser(user);
        message.setChat(chat);
        message.setTimeStamp(LocalDateTime.now());
        messageRepository.save(message);
        return messageMapper.toMessageResponse(message);
    }

    @Override
    public List<MessageResponse> findMessagesByChatId(String chatId)
    {
        List<Message> messages = messageRepository.findByChatId(chatId);
        return messageMapper.toListMessageResponse(messages);
    }

    @Override
    public Message findMessageById(String messageId)
    {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new AppException(ErrorCode.MESSAGE_NOT_EXIST));
    }

    @Override
    public MessageResponse findMessageByIdResponse(String messageId)
    {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new AppException(ErrorCode.MESSAGE_NOT_EXIST));
        return messageMapper.toMessageResponse(message);
    }
}
