package com.teya.bank.error;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(Long accountId){
        super("Insufficient balance for the Account with id: %d".formatted(accountId));
    }
}
