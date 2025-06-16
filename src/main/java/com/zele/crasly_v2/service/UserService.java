package com.zele.crasly_v2.service;

import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.ChatMapper;
import com.zele.crasly_v2.mapper.UserMapper;
import com.zele.crasly_v2.models.dto.chat.ChatViewDTO;
import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import com.zele.crasly_v2.models.entities.User;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserViewDTO> getAllUsers();

    ResponseEntity<UserViewDTO> getUserById(Long id);

    List<ChatViewDTO> getAllChatsUserIsIn(Long userId);

    Set<User> getAllUsersByUserId(List<Long> userIds);
}
