package com.teya.bank.service;

import com.teya.bank.entity.Account;
import com.teya.bank.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByAccount(Account account);
}
