package com.zele.crasly_v2.models;

import com.zele.crasly_v2.models.enums.SignInStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String userName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private SignInStatus signInStatus;

    @ManyToMany(mappedBy = "users")
    private Set<Chat> chats = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatMessage> chatMessages = new HashSet<>();
}
