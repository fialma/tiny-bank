package com.teya.bank.error;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("No User found with id: %d".formatted(id));
    }
}
