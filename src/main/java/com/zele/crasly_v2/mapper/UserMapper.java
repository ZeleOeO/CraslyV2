package com.zele.crasly_v2.mapper;

import com.zele.crasly_v2.models.dto.user.UserSignUpRequest;
import com.zele.crasly_v2.models.enums.SignInStatus;
import com.zele.crasly_v2.models.entities.User;
import com.zele.crasly_v2.models.dto.user.UserViewDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserViewDTO toUserView(User user) {
        UserViewDTO userViewDto = new UserViewDTO();
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
