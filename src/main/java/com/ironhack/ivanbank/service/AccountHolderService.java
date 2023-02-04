package com.ironhack.ivanbank.service;

import com.ironhack.ivanbank.dto.AccountHolderDTO;
import com.ironhack.ivanbank.model.AccountHolder;
import com.ironhack.ivanbank.model.User;
import com.ironhack.ivanbank.repository.AccountHolderRepository;
import com.ironhack.ivanbank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountHolderService {

    private final AccountHolderRepository accountHolderRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        var accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setPassport((accountHolderDTO.getPassport()));
        accountHolder.setPrimaryAddress(accountHolderDTO.getPrimaryAddress());
        accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
        var accountHolderFinal = accountHolderRepository.save(accountHolder);
        var user = new User(accountHolderFinal.getPassport(), passwordEncoder.encode(accountHolderFinal.getName()), "ROLE_USER", accountHolderFinal);
        userRepository.save(user);
        return accountHolderFinal;
    }
}
