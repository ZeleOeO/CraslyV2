package com.zele.crasly_v2.security;

import com.zele.crasly_v2.exceptions.user.UserNotFoundException;
import com.zele.crasly_v2.models.entities.User;
import com.zele.crasly_v2.models.security.UserPrincipal;
import com.zele.crasly_v2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UserNotFoundException("User with name " + username + " not found");
        return new UserPrincipal(user);
    }
}
