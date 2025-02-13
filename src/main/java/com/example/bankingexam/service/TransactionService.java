package com.example.bankingexam.service;

import com.example.bankingexam.model.Transaction;
import com.example.bankingexam.model.TransactionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;

public class TransactionService {
    private final ConcurrentHashMap<Long, List<Transaction>> transactions = new ConcurrentHashMap<>();
    private long currentTransactionId = 1;
    private final BankAccountService bankAccountService;

    public TransactionService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public void recordTransaction(long accountId, double amount, TransactionType type) {

        // Implement logic here
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (bankAccountService.getAccount(accountId) == null) {
            throw new IllegalArgumentException("Account does not exist");
        }

        bankAccountService.getAccount(accountId);

        Transaction transaction = new Transaction(currentTransactionId++, accountId, amount, type, LocalDateTime.now());

        List<Transaction> accountTransactions = transactions.get(accountId);
        if (accountTransactions == null) {
            accountTransactions = new ArrayList<>();
            transactions.put(accountId, accountTransactions);
        }

        accountTransactions.add(transaction);

    }

    public List<Transaction> getTransactionsForAccount(long accountId) {

        // Implement logic here
        List<Transaction> accountTransactions = transactions.getOrDefault(accountId, new ArrayList<>());
        accountTransactions.sort((t1, t2 ) -> t1.getTimestamp().compareTo(t2.getTimestamp()));
        return accountTransactions; // Temporary return, you should replace it with the appropriate value according to the method's logic.

    }


}
