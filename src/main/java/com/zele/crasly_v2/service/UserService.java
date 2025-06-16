package com.zele.crasly_v2.service;

import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import com.zele.crasly_v2.models.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserViewDTO> getAllUsers();

    ResponseEntity<UserViewDTO> getUserById(Long id);

    List<ChatViewDTO> getAllChatsUserIsIn(Long userId);

    Set<User> getAllUsersByUserId(List<Long> userIds);
}
