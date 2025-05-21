package com.zele.crasly_v2.mapper;

import com.zele.crasly_v2.dto.user.UserSignInRequest;
import com.zele.crasly_v2.dto.user.UserSignUpRequest;
import com.zele.crasly_v2.models.SignInStatus;
import com.zele.crasly_v2.models.User;
import com.zele.crasly_v2.dto.user.UserViewDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserViewDto toUserView(User user) {
        UserViewDto userViewDto = new UserViewDto();
        userViewDto.setUsername(user.getUserName());
        userViewDto.setEmail(user.getEmail());
        return userViewDto;
    }

    public User signUpRequestToUser(UserSignUpRequest request) {
        User user = new User();
        user.setUserName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setSignInStatus(SignInStatus.SIGNED_OUT);
        return user;
    }
}
