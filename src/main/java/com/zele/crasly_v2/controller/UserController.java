package com.zele.crasly_v2.controller;

import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import com.zele.crasly_v2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{userId}/chats")
    public List<ChatViewDTO> getUserChats(@PathVariable Long userId) {
        return userService.getAllChatsUserIsIn(userId);
    }
}
