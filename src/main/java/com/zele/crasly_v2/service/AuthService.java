package com.zele.crasly_v2.service;

import com.zele.crasly_v2.exceptions.user.UserAlreadyExistsException;
import com.zele.crasly_v2.exceptions.user.UserCreationErrorException;
import com.zele.crasly_v2.exceptions.user.UserNotAuthorizedException;
import com.zele.crasly_v2.mapper.UserMapper;
import com.zele.crasly_v2.models.dto.user.UserSignInRequest;
import com.zele.crasly_v2.models.dto.user.UserSignUpRequest;
import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public ResponseEntity<UserViewDTO> signup(UserSignUpRequest request) {
        var user = userMapper.signUpRequestToUser(request);
        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        if (!request.validatePassword()) throw new UserCreationErrorException("Passwords don't match");
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserView(user));
    }

    public ResponseEntity<Object> login(UserSignInRequest request) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (!authentication.isAuthenticated()) throw new UserNotAuthorizedException("User is not authenticated");
        return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(request.getEmail()));
    }
}
