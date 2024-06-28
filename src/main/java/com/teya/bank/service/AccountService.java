package com.teya.bank.service;

import com.teya.bank.entity.Account;
import com.teya.bank.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account create(User user);
    List<Account> findByUser(User user);
    Account findById(Long accountId);
    Account deposit(Account account, BigDecimal amount);
    Account withdraw(Account account, BigDecimal amount);
    Account transferTo(Account fromAccount, Account toAccount, BigDecimal amount);
}
