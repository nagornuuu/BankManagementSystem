package com.example.bankingexam.service;

import com.example.bankingexam.exception.InsufficientFundsException;
import com.example.bankingexam.model.BankAccount;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankAccountService {
    private final Map<Long, BankAccount> accounts = new HashMap<>();
    private long currentId = 1;

    public BankAccount createAccount(String accountHolder, double initialBalance) {

        // Implement logic here
        if (accountHolder == null || accountHolder.isEmpty()) {
            throw new IllegalArgumentException("Account holder is null or empty");
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance can't be negative");
        }

        BankAccount account = new BankAccount(currentId, accountHolder, initialBalance);
        accounts.put(currentId, account);
        currentId++;
        return account; // Temporary return, you should replace it with the appropriate value according to the method's logic.

    }

    public void deposit(long accountId, double amount) {

        // Implement logic here
        if (amount < 0) {
            throw new IllegalArgumentException("Amount can't be negative");
        }

        BankAccount account = accounts.get(accountId);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        account.setBalance(account.getBalance() + amount);
    }

    public void withdraw(long accountId, double amount) {

        // Implement logic here
        if (amount < 0) {
            throw new IllegalArgumentException("Amount can't be negative");
        }

        BankAccount account = accounts.get(accountId);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
    }

    public BankAccount getAccount(long accountId) {

        // Implement logic here
        BankAccount account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        return account; // Temporary return, you should replace it with the appropriate value according to the method's logic.
    }
}
