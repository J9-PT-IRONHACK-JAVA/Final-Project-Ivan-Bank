package com.ironhack.ivanbank.controller;

import com.ironhack.ivanbank.dto.TransactionDTO;
import com.ironhack.ivanbank.exception.AccountPermissionDeniedException;
import com.ironhack.ivanbank.model.Transaction;
import com.ironhack.ivanbank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @RequestMapping("/deposit")
    public Transaction createDeposit(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.createDeposit(transactionDTO);
    }

    @PostMapping
    @RequestMapping("/withdraw")
    public Transaction createWithdraw(@RequestBody TransactionDTO transactionDTO) throws AccountPermissionDeniedException {
        return transactionService.createWithdraw(transactionDTO);
    }

    @PostMapping
    @RequestMapping("/transfer")
    public Transaction createTransfer(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.createTransfer(transactionDTO);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAll();
    }
}
