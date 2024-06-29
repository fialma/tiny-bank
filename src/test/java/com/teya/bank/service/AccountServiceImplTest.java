package com.teya.bank.service;

import com.teya.bank.entity.Account;
import com.teya.bank.entity.Transaction;
import com.teya.bank.entity.User;
import com.teya.bank.error.AccountNotFoundException;
import com.teya.bank.error.InsufficientBalanceException;
import com.teya.bank.repository.AccountRepository;
import com.teya.bank.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testCreate() {
        User user = new User();
        user.setId(1L);
        user.setActive(true);
        when(accountRepository.save(any(Account.class))).thenReturn(new Account());

        accountService.create(user);

        verify(accountRepository).save(argThat(a ->
                a.getUser().equals(user)
        ));
    }

    @Test
    public void testFindById() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> accountService.findById(1L));
    }

    @Test
    public void testDeposit() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.ZERO);

        BigDecimal depositAmount = BigDecimal.valueOf(100);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account updatedAccount = accountService.deposit(account, depositAmount);

        assertNotNull(updatedAccount);
        assertEquals(depositAmount, updatedAccount.getBalance());

        // Verify that a transaction was created and saved
        verify(transactionRepository, times(1)).save(any(Transaction.class));

        // Verify that the account was updated
        verify(accountRepository, times(1)).save(account);

        // Check transaction
        verify(transactionRepository).save(argThat(t ->
                t.getAccount().equals(account) &&
                        t.getAmount().equals(depositAmount) &&
                        t.getType() == Transaction.TransactionType.DEPOSIT
        ));
    }

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(100));

        BigDecimal withdrawAmount = BigDecimal.valueOf(100);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account updatedAccount = accountService.withdraw(account, withdrawAmount);

        assertNotNull(updatedAccount);
        assertEquals(BigDecimal.ZERO, updatedAccount.getBalance());

        // Verify that a transaction was created
        verify(transactionRepository, times(1)).save(any(Transaction.class));

        // Verify that the account was updated
        verify(accountRepository, times(1)).save(account);

        // Check the transaction
        verify(transactionRepository).save(argThat(t ->
                t.getAccount().equals(account) &&
                        t.getAmount().equals(withdrawAmount) &&
                        t.getType() == Transaction.TransactionType.WITHDRAWAL
        ));
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(100));

        BigDecimal withdrawAmount = BigDecimal.valueOf(120);

        assertThrows(InsufficientBalanceException.class, () -> accountService.withdraw(account, withdrawAmount));

    }

    @Test
    public void testTransfer() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(BigDecimal.valueOf(500));

        Account toAccount = new Account();
        toAccount.setId(2L);
        toAccount.setBalance(BigDecimal.valueOf(200));

        BigDecimal transferAmount = BigDecimal.valueOf(100);

        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account resultAccount = accountService.transferTo(fromAccount, toAccount, transferAmount);

        assertNotNull(resultAccount);
        assertEquals(fromAccount, resultAccount);
        assertEquals(BigDecimal.valueOf(400), fromAccount.getBalance());
        assertEquals(BigDecimal.valueOf(300), toAccount.getBalance());

        // Verify transactions were saved
        verify(transactionRepository, times(2)).save(any(Transaction.class));
        verify(accountRepository, times(2)).save(any(Account.class));

        // transactions
        verify(transactionRepository).save(argThat(t ->
                t.getAccount().equals(toAccount) &&
                        t.getAmount().equals(transferAmount) &&
                        t.getType() == Transaction.TransactionType.TRANSFER_IN
        ));

        verify(transactionRepository).save(argThat(t ->
                t.getAccount().equals(fromAccount) &&
                        t.getAmount().equals(transferAmount) &&
                        t.getType() == Transaction.TransactionType.TRANSFER_OUT
        ));
    }


}
