package com.teya.bank.error;

public class UserNotActiveException extends RuntimeException{
    public UserNotActiveException(Long id){
        super("User with id: %d is not Active" .formatted(id));
    }
}
