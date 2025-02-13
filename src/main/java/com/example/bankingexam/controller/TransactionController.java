package com.example.bankingexam.controller;

import com.example.bankingexam.exception.InsufficientFundsException;
import com.example.bankingexam.model.Transaction;
import com.example.bankingexam.model.TransactionType;
import com.example.bankingexam.service.BankAccountService;
import com.example.bankingexam.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for managing transactions related operations
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final BankAccountService accountService;

    /**
     * Constructor that initialize the controller with an BankAccountService and sets TransactionService to null
     *
     * @param accountService the service handling account operations
     */
    public TransactionController(BankAccountService accountService) {
        this(null, accountService);
    }

    /**
     * Constructor for services
     *
     * @param transactionService the service handling transactions operations
     * @param accountService the service handling account operations
     */
    @Autowired
    public TransactionController(TransactionService transactionService, BankAccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    /**
     * Method which records a deposit transaction for an account with special id
     *
     * @param accountId the id of the account to deposit
     * @param amount the amount of money to deposit
     * @return a message which confirms that deposit was successful
     */
    @PostMapping("/record/deposit")
    public String recordDeposit(@RequestParam long accountId, @RequestParam double amount) {

        accountService.deposit(accountId, amount);
        transactionService.recordTransaction(accountId, amount, TransactionType.DEPOSIT);
        return "Deposit was successful";

    }

    /**
     * Method which records a withdrawal transaction for an account with special id
     *
     * @param accountId the id of the account to withdraw from
     * @param amount the amount of money to withdraw from
     * @return a message which confirms that withdraw was successful
     */
    @PostMapping("/record/withdraw")
    public String recordWithdraw(@RequestParam long accountId, @RequestParam double amount) throws InsufficientFundsException {

        accountService.withdraw(accountId, amount);
        transactionService.recordTransaction(accountId, amount, TransactionType.WITHDRAWAL);
        return "Withdrawal was successful";

    }

    /**
     * Method which show the list of transactions for special account
     *
     * @param accountId the id of an account to show its transactions
     * @return the list of transactions of special account
     */
    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(@PathVariable long accountId) {

        return transactionService.getTransactionsForAccount(accountId);

    }
}
