package com.zele.crasly_v2.service;

import com.zele.crasly_v2.models.dto.user.UserSignInRequest;
import com.zele.crasly_v2.models.dto.user.UserSignUpRequest;
import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<UserViewDTO> signup(UserSignUpRequest request);

    ResponseEntity<Object> login(UserSignInRequest request);
}
