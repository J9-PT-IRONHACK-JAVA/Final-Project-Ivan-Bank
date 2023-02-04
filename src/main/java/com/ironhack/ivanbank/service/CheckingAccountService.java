package com.ironhack.ivanbank.service;

import com.ironhack.ivanbank.dto.CheckingAccountDTO;
import com.ironhack.ivanbank.exception.UserNotFoundException;
import com.ironhack.ivanbank.model.CheckingAccount;
import com.ironhack.ivanbank.repository.AccountHolderRepository;
import com.ironhack.ivanbank.repository.CheckingAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {

    private final CheckingAccountRepository checkingAccountRepository;
    private final AccountHolderRepository accountHolderRepository;

    public CheckingAccount createDefault(CheckingAccountDTO checkingAccountDTO) {
        var owner = accountHolderRepository.findById(checkingAccountDTO.getPrimaryOwner()).orElseThrow(() -> new UserNotFoundException("User with id '" + checkingAccountDTO.getPrimaryOwner() + "' not found"));
        var checkingAccount = new CheckingAccount();
        checkingAccount.setPrimaryOwner(owner);
        if (checkingAccountDTO.getSecondaryOwner() != null) {
            var secondaryOwner = accountHolderRepository.findById(checkingAccountDTO.getSecondaryOwner()).orElseThrow(() -> new UserNotFoundException("User with id '" + checkingAccountDTO.getSecondaryOwner() + "' not found"));
            checkingAccount.setSecondaryOwner(secondaryOwner);
        }
        return checkingAccountRepository.save(checkingAccount);
    }

    public CheckingAccount createCustom(CheckingAccountDTO checkingAccountDTO) {
        var owner = accountHolderRepository.findById(checkingAccountDTO.getPrimaryOwner()).orElseThrow(() -> new UserNotFoundException("User with id '" + checkingAccountDTO.getPrimaryOwner() + "' not found"));
        var checkingAccount = new CheckingAccount();
        checkingAccount.setPrimaryOwner(owner);
        if (checkingAccountDTO.getSecondaryOwner() != null) {
            var secondaryOwner = accountHolderRepository.findById(checkingAccountDTO.getSecondaryOwner()).orElseThrow(() -> new UserNotFoundException("User with id '" + checkingAccountDTO.getSecondaryOwner() + "' not found"));
            checkingAccount.setSecondaryOwner(secondaryOwner);
        }
        checkingAccount.setMinimumBalance(checkingAccountDTO.getMinimumBalance());
        checkingAccount.setPenaltyFee(checkingAccountDTO.getPenaltyFee());
        checkingAccount.setMonthlyMaintenanceFee(checkingAccountDTO.getMonthlyMaintenanceFee());
        return checkingAccountRepository.save(checkingAccount);
    }
}
