package com.teya.bank.error;

public class AccountNotActiveException extends RuntimeException{
    public AccountNotActiveException(Long userId, Long accountId){
        super("The User with id: %d owner of the Account id: %d is not Active".formatted(userId, accountId));
    }
}
