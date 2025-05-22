package com.zele.crasly_v2.exceptions.chatmessage;

public class ChatMessageNotFoundException extends RuntimeException {
    public ChatMessageNotFoundException(String message) {
        super(message);
    }
}
