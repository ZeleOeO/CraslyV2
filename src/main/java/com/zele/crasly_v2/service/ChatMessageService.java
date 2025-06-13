package com.zele.crasly_v2.service;

import com.zele.crasly_v2.exceptions.chat.ChatNotFoundException;
import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.ChatMessageMapper;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageCreateRequest;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageViewDTO;
import com.zele.crasly_v2.models.entities.ChatMessage;
import com.zele.crasly_v2.repository.ChatMessageRepository;
import com.zele.crasly_v2.repository.ChatRepository;
import com.zele.crasly_v2.repository.UserRepository;
import com.zele.crasly_v2.security.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.zele.crasly_v2.models.entities.User;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final JWTService jWTService;

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

    public ResponseEntity<ChatMessageViewDTO> createChatMessage(ChatMessageCreateRequest createRequest, Long chatId, HttpServletRequest request) {
        ChatMessage chatMessage = new ChatMessage();
        var user = getUserFromRequest(request);
        var chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) throw new ChatNotFoundException("Chat with id " + chatId + " not found");
        if (!chat.getUsers().contains(user)) throw new UserNotFoundException("User with id " + user.getId() + " not in chat");
        chatMessage.setSender(user);
        chatMessage.setChat(chat);
        chatMessage.getText().setContent(createRequest.getMessage());
        chatMessageRepository.save(chatMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMessageMapper.toChatMessageViewDTO(chatMessage));
    }

    public ResponseEntity<ChatMessageViewDTO> replyChatMessage(ChatMessageCreateRequest replyRequest, Long chatId, Long chatMessageId, HttpServletRequest servletRequest) {
        ChatMessage chatMessage = new ChatMessage();
        var user = getUserFromRequest(servletRequest);
        var chat = chatRepository.findById(chatId).orElse(null);
        var parentMessage = chatMessageRepository.findById(chatMessageId).orElse(null);
        if (chat == null) throw new ChatNotFoundException("Chat with id " + chatId + " not found");
        if (parentMessage == null) throw new ChatNotFoundException("Chat with id " + chatMessageId + " not found");
        if (!chat.getUsers().contains(user)) throw new UserNotFoundException("User with id " + user.getId() + " not found");
        if (!chat.getHistory().contains(parentMessage))
            throw new ChatNotFoundException("Chat with id " + chatMessageId + " not found");
        chatMessage.setSender(user);
        chatMessage.setChat(chat);
        chatMessage.getText().setContent(replyRequest.getMessage());
        chatMessage.setParentMessage(parentMessage);
        chatMessageRepository.save(chatMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMessageMapper.toChatMessageViewDTO(chatMessage));
    }

    // Helper Methods
    private User getUserFromRequest(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring(7);
        String email = jWTService.getUsername(authToken);
        var user = userRepository.findByEmail(email);
        if (user == null) throw new UserNotFoundException("User with email " + email + " not found");
        return user;
    }
}
