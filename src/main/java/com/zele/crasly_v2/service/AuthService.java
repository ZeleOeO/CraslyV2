package com.zele.crasly_v2.service;

import com.zele.crasly_v2.dto.user.UserSignInRequest;
import com.zele.crasly_v2.dto.user.UserSignUpRequest;
import com.zele.crasly_v2.dto.user.UserViewDto;
import com.zele.crasly_v2.exceptions.user.UserAlreadyExistsException;
import com.zele.crasly_v2.exceptions.user.UserCreationErrorException;
import com.zele.crasly_v2.exceptions.user.UserNotAuthorizedException;
import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.mapper.UserMapper;
import com.zele.crasly_v2.models.SignInStatus;
import com.zele.crasly_v2.models.User;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public ResponseEntity<UserViewDto> signup (UserSignUpRequest request) {
       var user = userMapper.signUpRequestToUser(request);
       if (userRepository.existsByEmail(request.getEmail())) throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
       if (!request.validatePassword()) throw new UserCreationErrorException("Passwords don't match");
       userRepository.save(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserView(user));
    }

    public ResponseEntity<UserViewDto> login (UserSignInRequest request) {
        User user;
        if (request.isEmail()) {user = userRepository.findByEmail(request.getUsernameOrEmail());}
        else {user = userRepository.findByUserName(request.getUsernameOrEmail());}
        if (user == null) throw new UserNotFoundException("User with name " + request.getUsernameOrEmail() + " not found");
        if (!user.getPassword().equals(request.getPassword())) throw new UserNotAuthorizedException("User " + request.getUsernameOrEmail() + " password is incorrect");
        user.setSignInStatus(SignInStatus.SIGNED_IN);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserView(user));
    }
}
