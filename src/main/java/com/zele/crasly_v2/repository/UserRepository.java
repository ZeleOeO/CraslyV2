package com.zele.crasly_v2.repository;

import com.zele.crasly_v2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
