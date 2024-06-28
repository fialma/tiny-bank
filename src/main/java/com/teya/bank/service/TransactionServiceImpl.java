package com.teya.bank.service;

import com.teya.bank.entity.Account;
import com.teya.bank.entity.Transaction;
import com.teya.bank.entity.User;
import com.teya.bank.error.AccountNotFoundException;
import com.teya.bank.error.InsufficientBalanceException;
import com.teya.bank.repository.AccountRepository;
import com.teya.bank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByAccount(Account account) {
        return transactionRepository.findByAccountId(account.getId());
    }
}
