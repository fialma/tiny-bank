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
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Account create(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findByUser(User user) {
        return accountRepository.findByUserId(user.getId());
    }

    @Override
    public Account findById(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        }
        throw new AccountNotFoundException(accountId);
    }

    @Override
    public Account deposit(Account account, BigDecimal amount) {
        return deposit(account, amount, false);
    }

    public Account deposit(Account account, BigDecimal amount, boolean isTransfer) {
        account.setBalance(account.getBalance().add(amount));
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setType(isTransfer ?
                Transaction.TransactionType.TRANSFER_IN : Transaction.TransactionType.DEPOSIT);
        transactionRepository.save(transaction);
        return accountRepository.save(account);
    }


    @Override
    public Account withdraw(Account account, BigDecimal amount) {
        return withdraw(account, amount, false);
    }

    public Account withdraw(Account account, BigDecimal amount, boolean isTransfer) {
        BigDecimal updatedBalance = account.getBalance().subtract(amount);

        if(updatedBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new InsufficientBalanceException(account.getId());

        account.setBalance(updatedBalance);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setType(isTransfer ?
                Transaction.TransactionType.TRANSFER_OUT : Transaction.TransactionType.WITHDRAWAL);
        transactionRepository.save(transaction);
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account transferTo(Account fromAccount, Account toAccount, BigDecimal amount) {
        deposit(toAccount, amount, true);
        return withdraw(fromAccount, amount, true);
    }
}
