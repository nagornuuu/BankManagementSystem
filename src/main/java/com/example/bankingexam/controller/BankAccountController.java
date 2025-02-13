package com.example.bankingexam.controller;

import com.example.bankingexam.dto.DepositRequest;
import com.example.bankingexam.dto.WithdrawRequest;
import com.example.bankingexam.exception.InsufficientFundsException;
import com.example.bankingexam.model.BankAccount;
import com.example.bankingexam.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Rest Controller for managing bank accounts related operations
 */
@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    /** Service layer to handle logic for banking account*/
    @Autowired
    private final BankAccountService accountService;

    /**
     * Default constructor
     */
    public BankAccountController() {
        this(null);
    }

    /**
     * Constructor for account service
     *
     * @param accountService the service handling account operations
     */
    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Method for creating an account for bank
     *
     * @param accountHolder the name of the owner of an account
     * @param initialBalance the balance which will be on an account
     * @return a created account object
     */
    @PostMapping("/create")
    public BankAccount createAccount(@RequestParam String accountHolder, @RequestParam double initialBalance) {

        return accountService.createAccount(accountHolder, initialBalance);

    }

    /**
     * Method for getting account by id
     *
     * @param id the id of an account to be found
     * @return an object of account with specified id
     */
    @GetMapping("/{id}")
    public BankAccount getAccount(@PathVariable long id) {

        return accountService.getAccount(id);
    }

    /**
     * Method for depositing money into an account
     *
     * @param request which contains the account id and amount to deposit
     */
    @PostMapping("/deposit")
    public void deposit(@Valid @RequestBody DepositRequest request) {

        accountService.deposit(request.getAccountId(), request.getAmount());

    }

    /**
     * Method for withdrawing money from an account
     *
     * @param request which contains the account id and amount to withdraw
     */
    @PostMapping("/withdraw")
    public void withdraw(@Valid @RequestBody WithdrawRequest request) throws InsufficientFundsException {

        accountService.withdraw(request.getAccountId(), request.getAmount());

    }
}
