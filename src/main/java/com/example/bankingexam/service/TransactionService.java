package com.example.bankingexam.service;

import com.example.bankingexam.model.Transaction;
import com.example.bankingexam.model.TransactionType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;

@Service
public class TransactionService {
    private final ConcurrentHashMap<Long, List<Transaction>> transactions = new ConcurrentHashMap<>();
    private long currentTransactionId = 1;
    private final BankAccountService bankAccountService;

    public TransactionService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /**
     * Method which is responsible for recording the transaction of a specific bank account
     * Validations:
     * 1. Checks if amount to deposit/withdraw is more than 0
     * 2. Checks if account exists or no
     *
     * @param accountId the id of an account to which transactions should be recorded
     * @param amount the amount of money of to be deposited/withdraw
     * @param type the type of transaction
     */
    public void recordTransaction(long accountId, double amount, TransactionType type) {

        if (amount <= 0) {
            throw new IllegalArgumentException("The amount must be greater than zero");
        }

        if (bankAccountService.getAccount(accountId) == null) {
            throw new NullPointerException("Account does not exist");
        }

        bankAccountService.getAccount(accountId);
        Transaction transaction = new Transaction(currentTransactionId++, accountId, amount, type, LocalDateTime.now());

        // Retrieves the list of transactions for the special account
        // if no transactions , create an empty list and adds it to the Map
        List<Transaction> accountTransactions = transactions.get(accountId);
        if (accountTransactions == null) {
            accountTransactions = new ArrayList<>();
            transactions.put(accountId, accountTransactions);
        }

        accountTransactions.add(transaction);

    }

    /**
     * The method which shows the list of transactions of special account
     *
     * @param accountId the id of an account from which we can take transactions
     * @return the list of transactions for special account
     */
    public List<Transaction> getTransactionsForAccount(long accountId) {

        List<Transaction> accountTransactions = transactions.get(accountId);

        if (accountTransactions == null || accountTransactions.isEmpty()) {
            return new ArrayList<>();
        }

        // sorting the transactions by timestamp
        accountTransactions.sort((t1, t2 ) -> t1.getTimestamp().compareTo(t2.getTimestamp()));
        return accountTransactions;

    }
}
