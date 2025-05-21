package com.zele.crasly_v2.service;

import com.zele.crasly_v2.exceptions.chat.ChatNotFoundException;
import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.ChatMapper;
import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.repository.ChatRepository;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private final UserRepository userRepository;

    public List<ChatViewDTO> getAllChats() {
        return chatRepository.findAll()
                .stream()
                .map(chatMapper::toChatViewDTO)
                .toList();
    }

    public ResponseEntity<ChatViewDTO> getChatById(Long id) {
        var chat = chatRepository.findById(id).orElse(null);
        if (chat == null) throw new ChatNotFoundException("Chat with id " + id + " not found");
        return ResponseEntity.status(HttpStatus.OK).body(chatMapper.toChatViewDTO(chat));
    }

    public ResponseEntity<ChatViewDTO> createChat(ChatCreateRequest createRequest) {
        var chat = chatMapper.chatCreateRequestToChat(createRequest);
        var user = userRepository.findById(createRequest.getUserId()).orElse(null);
        if (user == null) throw new UserNotFoundException("User not found");
        chat.getUsers().add(user);
        chatRepository.save(chat);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMapper.toChatViewDTO(chat));
    }
}
