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

    /**
     * The method which creates and account
     *
     * Methods include validations:
     * 1. Checks for account holder's name if it null or empty
     * 2. Checks if initialBalance is more than 0
     *
     * @param accountHolder the name of holder of an account
     * @param initialBalance the balance of the account
     * @return the object of created account
     */
    public BankAccount createAccount(String accountHolder, double initialBalance) {

        if (accountHolder == null || accountHolder.isEmpty()) {
            throw new IllegalArgumentException("The account holder's name cannot be null or empty");
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException("The Initial balance cannot be negative");
        }

        BankAccount account = new BankAccount(currentId, accountHolder, initialBalance);
        accounts.put(currentId, account);
        currentId++;
        return account;

    }

    /**
     * Method which create a deposit for an account
     * Methods include validations:
     * 1. Checks if amount to deposit is more than 0
     * 2. Checks if account exists or no
     *
     * @param accountId the id of an account on which deposit should be done
     * @param amount the amount of money to be deposited
     */
    public void deposit(long accountId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("The amount must be greater than zero");
        }

        BankAccount account = accounts.get(accountId);

        if (account == null) {
            throw new NullPointerException("Account not found");
        }

        account.setBalance(account.getBalance() + amount);

    }

    /**
     * Method which create a withdrawal for an account
     * Methods include validations:
     * 1. Checks if amount to withdraw is more than 0
     * 2. Checks if account exists or no
     * 3. Checks the sufficient of account balance
     *
     * @param accountId the id of an account from which withdrawal should be done
     * @param amount the amount of money to withdraw from
     */
    public void withdraw(long accountId, double amount) throws InsufficientFundsException {

        if (amount <= 0) {
            throw new NullPointerException("The amount must be greater than zero");
        }

        BankAccount account = accounts.get(accountId);

        if (account == null) {
            throw new NullPointerException("Account not found");
        }

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

    }

    /**
     * Method which responsible to return an account with special id
     * Validations:
     * 1. Check if account exists
     *
     * @param accountId the id of an account to show
     * @return the account
     */
    public BankAccount getAccount(long accountId) {

        BankAccount account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException("Account not found");
        }

        return account;

    }
}
