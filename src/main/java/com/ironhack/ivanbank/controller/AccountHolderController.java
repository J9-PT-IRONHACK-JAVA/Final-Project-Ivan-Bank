package com.ironhack.ivanbank.controller;

import com.ironhack.ivanbank.dto.AccountHolderDTO;
import com.ironhack.ivanbank.model.AccountHolder;
import com.ironhack.ivanbank.repository.AccountHolderRepository;
import com.ironhack.ivanbank.repository.UserRepository;
import com.ironhack.ivanbank.service.AccountHolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-holder")
public class AccountHolderController {

    private final AccountHolderRepository accountHolderRepository;
    private final AccountHolderService accountHolderService;
    private final UserRepository userRepository;

    @PostMapping
    @RequestMapping("/create")
    public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolderDTO accountHolderDTO) {
        return accountHolderService.createAccountHolder(accountHolderDTO);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public void deleteAccountHolder(@PathVariable Long id) {
        accountHolderRepository.deleteById(id);
        userRepository.deleteByOwnerIs(accountHolderRepository.findById(id).get());
    }
}
