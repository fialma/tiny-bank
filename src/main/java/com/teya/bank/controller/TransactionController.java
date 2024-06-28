package com.teya.bank.controller;

import com.teya.bank.entity.Account;
import com.teya.bank.entity.Transaction;
import com.teya.bank.service.AccountService;
import com.teya.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/findByAccountId/{accountId}")
    public List<Transaction> findByAccountId(@PathVariable Long accountId){
        Account account = accountService.findById(accountId);
        return transactionService.findByAccount(account);
    }
}
