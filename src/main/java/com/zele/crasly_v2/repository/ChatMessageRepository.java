package com.zele.crasly_v2.repository;

import com.zele.crasly_v2.models.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findChatMessagesByChatId(Long chatId);

    List<ChatMessage> findChatMessagesBySenderId(Long senderId);
}
