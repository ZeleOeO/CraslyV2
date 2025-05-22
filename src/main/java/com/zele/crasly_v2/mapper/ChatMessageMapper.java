package com.zele.crasly_v2.mapper;

import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageViewDTO;
import com.zele.crasly_v2.models.entities.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper {
    public ChatMessageViewDTO toChatMessageViewDTO(ChatMessage chatMessage) {
        ChatMessageViewDTO chatMessageViewDTO = new ChatMessageViewDTO();
        chatMessageViewDTO.setId(chatMessage.getId());
        chatMessageViewDTO.setSenderName(chatMessage.getSender().getUserName());
        chatMessageViewDTO.setChatName(chatMessage.getChat().getChatName());
        chatMessageViewDTO.setChatMessage(chatMessage.getText().getContent());
        chatMessageViewDTO.setTimestamp(chatMessageViewDTO.getTimestamp());
        return chatMessageViewDTO;
    }
}
