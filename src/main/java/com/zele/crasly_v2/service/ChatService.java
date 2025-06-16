package com.zele.crasly_v2.service;

import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {
    List<ChatViewDTO> getAllChats();

    ResponseEntity<ChatViewDTO> getChatById(Long id);

    ResponseEntity<ChatViewDTO> createChat(ChatCreateRequest createRequest, HttpServletRequest request);
}
