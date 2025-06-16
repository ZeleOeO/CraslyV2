package com.zele.crasly_v2.service;

import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageCreateRequest;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageViewDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessageViewDTO> getMessagesByChatId(Long chatId);

    List<ChatMessageViewDTO> getMessagesBySenderId(Long senderId);

    ResponseEntity<ChatMessageViewDTO> createChatMessage(ChatMessageCreateRequest createRequest, Long chatId, HttpServletRequest request);

    ResponseEntity<ChatMessageViewDTO> replyChatMessage(ChatMessageCreateRequest replyRequest, Long chatId, Long chatMessageId, HttpServletRequest servletRequest);
}
