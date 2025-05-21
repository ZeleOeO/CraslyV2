package com.zele.crasly_v2.controller;

import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/all")
    public List<ChatViewDTO> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatViewDTO> getChatById(@PathVariable Long id) {
        return chatService.getChatById(id);
    }
}
