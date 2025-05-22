package com.zele.crasly_v2.service;

import com.zele.crasly_v2.exceptions.chat.ChatNotFoundException;
import com.zele.crasly_v2.exceptions.chatmessage.ChatMessageNotFoundException;
import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.ChatMessageMapper;
import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageCreateRequest;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageViewDTO;
import com.zele.crasly_v2.models.entities.Chat;
import com.zele.crasly_v2.models.entities.ChatMessage;
import com.zele.crasly_v2.repository.ChatMessageRepository;
import com.zele.crasly_v2.repository.ChatRepository;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public List<ChatMessageViewDTO> getMessagesByChatId(Long chatId) {
       return chatMessageRepository.findChatMessagesByChatId(chatId)
               .stream()
               .map(chatMessageMapper::toChatMessageViewDTO)
               .toList();
    }

    public List<ChatMessageViewDTO> getMessagesBySenderId(Long senderId) {
        return chatMessageRepository.findChatMessagesBySenderId(senderId)
                .stream()
                .map(chatMessageMapper::toChatMessageViewDTO)
                .toList();
    }

    public ResponseEntity<ChatMessageViewDTO> createChatMessage(ChatMessageCreateRequest createRequest, Long chatId, Long userId) {
        ChatMessage chatMessage = new ChatMessage();
        var user = userRepository.findById(userId).orElse(null);
        var chat = chatRepository.findById(chatId).orElse(null);
        if (user == null) throw new UserNotFoundException("User with id " + userId + " not found");
        if (chat == null) throw new ChatNotFoundException("Chat with id " + chatId + " not found");
        if (!chat.getUsers().contains(user)) throw new UserNotFoundException("User with id " + userId + " not in chat");
        chatMessage.setSender(user);
        chatMessage.setChat(chat);
        chatMessage.getText().setContent(createRequest.getMessage());
        chatMessageRepository.save(chatMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMessageMapper.toChatMessageViewDTO(chatMessage));
    }

    public ResponseEntity<ChatMessageViewDTO> replyChatMessage(ChatMessageCreateRequest replyRequest, Long chatId, Long userId, Long chatMessageId) {
        ChatMessage chatMessage = new ChatMessage();
        var user = userRepository.findById(userId).orElse(null);
        var chat = chatRepository.findById(chatId).orElse(null);
        var parentMessage = chatMessageRepository.findById(chatMessageId).orElse(null);
        if (user == null) throw new UserNotFoundException("User with id " + userId + " not found");
        if (chat == null) throw new ChatNotFoundException("Chat with id " + chatId + " not found");
        if (parentMessage == null) throw new ChatNotFoundException("Chat with id " + chatMessageId + " not found");
        if (!chat.getUsers().contains(user)) throw new UserNotFoundException("User with id " + userId + " not found");
        if (!chat.getHistory().contains(parentMessage)) throw new ChatNotFoundException("Chat with id " + chatMessageId + " not found");
        chatMessage.setSender(user);
        chatMessage.setChat(chat);
        chatMessage.getText().setContent(replyRequest.getMessage());
        chatMessage.setParentMessage(parentMessage);
        chatMessageRepository.save(chatMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMessageMapper.toChatMessageViewDTO(chatMessage));
    }
}
