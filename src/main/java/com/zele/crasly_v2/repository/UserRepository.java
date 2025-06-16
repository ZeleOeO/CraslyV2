package com.zele.crasly_v2.repository;

import com.zele.crasly_v2.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    Set<User> findByEmailIn(List<String> emails);
}
