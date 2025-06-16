package com.zele.crasly_v2.controller;

import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageCreateRequest;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageViewDTO;
import com.zele.crasly_v2.service.ChatMessageService;
import com.zele.crasly_v2.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final ChatMessageService chatMessageService;

    @GetMapping("/all")
    public List<ChatViewDTO> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatViewDTO> getChatById(@PathVariable Long id) {
        return chatService.getChatById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<ChatViewDTO> createChat(@RequestBody ChatCreateRequest chatCreateRequest, HttpServletRequest servletRequest) {
        return chatService.createChat(chatCreateRequest, servletRequest);
    }

    @GetMapping("/{chatId}/messages")
    public List<ChatMessageViewDTO> getChatMessages(
            @PathVariable Long chatId
    ) {
        return chatMessageService.getMessagesByChatId(chatId);
    }

    @PostMapping("/{chatId}/send-message")
    public ResponseEntity<ChatMessageViewDTO> sendChatMessage(
            @PathVariable Long chatId,
            @RequestBody ChatMessageCreateRequest request,
            HttpServletRequest servletRequest
    ) {
        return chatMessageService.createChatMessage(request, chatId, servletRequest);
    }

    @PostMapping("/{chatId}/reply-message/{messageId}")
    public ResponseEntity<ChatMessageViewDTO> replyChatMessage(
            @PathVariable Long chatId,
            @PathVariable Long messageId,
            @RequestBody ChatMessageCreateRequest createRequest,
            HttpServletRequest servletRequest
    ) {
        return chatMessageService.replyChatMessage(createRequest, chatId, messageId, servletRequest);
    }
}
