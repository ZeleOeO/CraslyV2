package com.zele.crasly_v2.service;

import com.zele.crasly_v2.exceptions.chat.ChatNotFoundException;
import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.ChatMapper;
import com.zele.crasly_v2.models.dto.chat.ChatCreateRequest;
import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.models.entities.User;
import com.zele.crasly_v2.repository.ChatRepository;
import com.zele.crasly_v2.repository.UserRepository;
import com.zele.crasly_v2.security.JWTService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final UserService userService;
    private final JWTService jWTService;

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

    public ResponseEntity<ChatViewDTO> createChat(ChatCreateRequest createRequest, HttpServletRequest request) {
        var chat = chatMapper.chatCreateRequestToChat(createRequest);
        var user = getUserFromRequest(request);
        chat.setUsers(userRepository.findByEmailIn(createRequest.getParticipants()));
        chat.getUsers().add(user);
        chatRepository.save(chat);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMapper.toChatViewDTO(chat));
    }

    // Helper Requests
    private User getUserFromRequest(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring(7);
        String email = jWTService.getUsername(authToken);
        var user = userRepository.findByEmail(email);
        if (user == null) throw new UserNotFoundException("User with email " + email + " not found");
        return user;
    }
}
