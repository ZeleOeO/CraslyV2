package com.zele.crasly_v2.mapper;

import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.models.entities.Chat;
import com.zele.crasly_v2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChatMapper {
    public ChatViewDTO toChatViewDTO(Chat chat) {
        ChatViewDTO chatViewDTO = new ChatViewDTO();
        chatViewDTO.setId(chat.getId());
        chatViewDTO.setChatName(chat.getChatName());
        chatViewDTO.setCreatedAt(chat.getCreatedAt());
        return chatViewDTO;
    }

    public Chat chatCreateRequestToChat(ChatCreateRequest createRequest) {
        Chat chat = new Chat();
        chat.setChatName(createRequest.getChatName());
        return chat;
    }
}