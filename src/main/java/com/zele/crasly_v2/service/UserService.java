package com.zele.crasly_v2.service;

import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.UserMapper;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private UserRepository userRepository;

    public List<UserViewDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserView)
                .toList();
    }

    public ResponseEntity<UserViewDTO> getUserById(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) throw new UserNotFoundException("User with id " + id + " not found");
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserView(user));
    }
}
