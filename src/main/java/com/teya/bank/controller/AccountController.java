package com.teya.bank.controller;

import com.teya.bank.entity.Account;
import com.teya.bank.entity.User;
import com.teya.bank.error.AccountNotActiveException;
import com.teya.bank.service.AccountService;
import com.teya.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * In case on object is not found
 * a Response with 404-NotFound is returned
 *
 * I assume that a User deactivated is not allow
 * to do operations like withdraw, deposit, creating
 * and receiving transfer, etc.
 * In case an operation is attempted on a deactivated account
 * a Response with 403-Forbidden is returned
 *
 * In case a withdraw or a transfer is attempted with
 * not sufficient balance on it also 403-Forbidden is returned
 *
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @GetMapping("/findByUserId/{userId}")
    public List<Account> findByUserId(@PathVariable Long userId){
        User user = userService.findById(userId);
        return accountService.findByUser(user);
    }

    @GetMapping("/findById/{accountId}")
    public Account findById(@PathVariable Long accountId){
        return accountService.findById(accountId);
    }

    @GetMapping("/balance/{accountId}")
    public BigDecimal balance(@PathVariable Long accountId){
        return accountService.findById(accountId).getBalance();
    }

    @PostMapping("/create/{userId}")
    public Account create(@PathVariable Long userId){
        User user = userService.findActiveUserById(userId);
        return accountService.create(user);
    }

    @PostMapping("/deposit/{accountId}")
    public Account deposit(@PathVariable Long accountId, @RequestBody BigDecimal amount){
        Account account = accountService.findById(accountId);
        if(!account.getUser().isActive())
            throw new AccountNotActiveException(accountId, account.getUser().getId());

        return accountService.deposit(account, amount);
    }

    @PostMapping("/withdraw/{accountId}")
    public Account withdraw(@PathVariable Long accountId, @RequestBody BigDecimal amount){
        Account account = accountService.findById(accountId);
        if(!account.getUser().isActive())
            throw new AccountNotActiveException(accountId, account.getUser().getId());

        return accountService.withdraw(account, amount);
    }

    @PostMapping("/transfer/{fromAccountId}/{toAccountId}")
    public Account transferTo(@PathVariable Long fromAccountId, @PathVariable Long toAccountId, @RequestBody BigDecimal amount){
        Account fromAccount = accountService.findById(fromAccountId);
        if(!fromAccount.getUser().isActive())
            throw new AccountNotActiveException(fromAccountId, fromAccount.getUser().getId());

        Account toAccount = accountService.findById(toAccountId);
        if(!toAccount.getUser().isActive())
            throw new AccountNotActiveException(toAccountId, toAccount.getUser().getId());

        return accountService.transferTo(fromAccount, toAccount, amount);
    }
}
