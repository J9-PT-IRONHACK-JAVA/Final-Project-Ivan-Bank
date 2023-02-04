package com.ironhack.ivanbank.service;

import com.ironhack.ivanbank.dto.TransactionDTO;
import com.ironhack.ivanbank.exception.AccountNotFoundException;
import com.ironhack.ivanbank.exception.AccountPermissionDeniedException;
import com.ironhack.ivanbank.exception.UserNotFoundException;
import com.ironhack.ivanbank.model.Transaction;
import com.ironhack.ivanbank.model.TransactionType;
import com.ironhack.ivanbank.model.utils.Money;
import com.ironhack.ivanbank.repository.CheckingAccountRepository;
import com.ironhack.ivanbank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CheckingAccountRepository checkingAccountRepository;
    public Transaction createDeposit(TransactionDTO transactionDTO) {
        var transaction = new Transaction();
        var destination = checkingAccountRepository.findById(transactionDTO.getDestinationId()).orElseThrow(() -> new UserNotFoundException("Account with id '" + transactionDTO.getDestinationId() + "' not found"));
        transaction.setDestination(destination);
        transaction.setDestinationName(destination.getPrimaryOwner().getName());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setConcept(transactionDTO.getConcept());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        destination.getBalance().increaseAmount(transaction.getAmount());
        checkingAccountRepository.save(destination);
        return transactionRepository.save(transaction);
    }

    public Transaction createWithdraw(TransactionDTO transactionDTO) throws AccountPermissionDeniedException {
        var transaction = new Transaction();

        var origin = checkingAccountRepository.findById(transactionDTO.getOriginId()).orElseThrow(() -> new AccountNotFoundException("Account with id '" + transactionDTO.getOriginId() + "' not found"));

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();

//        if (!username.equals(origin.getPrimaryOwner().getName()) && (origin.getSecondaryOwner() == null || !username.equals(origin.getSecondaryOwner().getName()))) {
//            throw new AccountPermissionDeniedException(username + " permission denied to access account " + origin.getId());
//        }

//        if (!username.equals(origin.getPrimaryOwner().getName())) {
//            throw new AccountPermissionDeniedException(username + " permission denied to access account " + origin.getId());
//        }

        transaction.setOrigin(origin);
        transaction.setOriginName(origin.getPrimaryOwner().getName());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setConcept(transactionDTO.getConcept());
        transaction.setTransactionType(TransactionType.WITHDRAW);

        origin.getBalance().decreaseAmount(transactionDTO.getAmount());
        checkingAccountRepository.save(origin);

        return transactionRepository.save(transaction);
    }

    public Transaction createTransfer(TransactionDTO transactionDTO) {
        var transaction = new Transaction();

        var origin = checkingAccountRepository.findById(transactionDTO.getOriginId()).orElseThrow(() -> new AccountNotFoundException("Account with id '" + transactionDTO.getOriginId() + "' not found"));
        var destination = checkingAccountRepository.findById(transactionDTO.getDestinationId()).orElseThrow(() -> new AccountNotFoundException("Account with id '" + transactionDTO.getDestinationId() + "' not found"));

        transaction.setOrigin(origin);
        transaction.setOriginName(origin.getPrimaryOwner().getName());
        transaction.setDestination(destination);
        transaction.setDestinationName(destination.getPrimaryOwner().getName());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setConcept(transactionDTO.getConcept());
        transaction.setTransactionType(TransactionType.TRANSFER);

        origin.getBalance().decreaseAmount(transaction.getAmount());
        destination.getBalance().increaseAmount(transaction.getAmount());
        checkingAccountRepository.save(origin);
        checkingAccountRepository.save(destination);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAll() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();

        return transactionRepository.findAllByOrigin_PrimaryOwner_Passport_OrDestination_PrimaryOwner_Passport(username, username);
    }
}
