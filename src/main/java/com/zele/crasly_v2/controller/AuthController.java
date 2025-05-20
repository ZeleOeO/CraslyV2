package com.zele.crasly_v2.controller;

import com.zele.crasly_v2.dto.user.UserSignInRequest;
import com.zele.crasly_v2.dto.user.UserSignUpRequest;
import com.zele.crasly_v2.dto.user.UserViewDto;
import com.zele.crasly_v2.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserViewDto> signIn(@RequestBody UserSignUpRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/log-in")
    public ResponseEntity<UserViewDto> logIn(@RequestBody UserSignInRequest request) {
       return authService.login(request);
    }
}