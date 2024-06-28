package com.teya.bank.error;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Long id){
        super("No Account found with id: %d".formatted(id));
    }

}
