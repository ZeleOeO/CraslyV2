package com.zele.crasly_v2.exceptions.user;

public class UserCreationErrorException extends RuntimeException {
    public UserCreationErrorException(String message) {
        super(message);
    }
}
