package com.zele.crasly_v2.repository;

import com.zele.crasly_v2.models.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
