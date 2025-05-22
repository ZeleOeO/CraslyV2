package com.zele.crasly_v2.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zele.crasly_v2.models.dto.chatmessage.ChatMessageViewDTO;
import com.zele.crasly_v2.models.entities.ChatMessage;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessageMapper {
    public ChatMessageViewDTO toChatMessageViewDTO(ChatMessage chatMessage) {
        ChatMessageViewDTO chatMessageViewDTO = new ChatMessageViewDTO();
        chatMessageViewDTO.setId(chatMessage.getId());
        chatMessageViewDTO.setSenderName(chatMessage.getSender().getUserName());
        chatMessageViewDTO.setChatName(chatMessage.getChat().getChatName());
        chatMessageViewDTO.setChatMessage(chatMessage.getText().getContent());
        chatMessageViewDTO.setTimestamp(chatMessage.getSentAt());
        chatMessageViewDTO.setReplies(
                new HashMap<>(chatMessage.getReplies()
                        .stream()
                        .collect(Collectors.toMap(
                                chatMessage2 -> chatMessage2.getSender().getUserName(),
                                chatMessage1 -> chatMessage1.getText().getContent())))
        );
        if (chatMessageViewDTO.getReplies().isEmpty()) chatMessageViewDTO.setReplies(null);
        return chatMessageViewDTO;
    }
}
