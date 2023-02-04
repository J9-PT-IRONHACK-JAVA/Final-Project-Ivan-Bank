package com.ironhack.ivanbank.controller;

import com.ironhack.ivanbank.dto.CheckingAccountDTO;
import com.ironhack.ivanbank.model.CheckingAccount;
import com.ironhack.ivanbank.service.CheckingAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checking-account")
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;

    @PostMapping
    @RequestMapping("/create-default")
    public CheckingAccount createDefault(@RequestBody @Valid CheckingAccountDTO checkingAccountDTO) {
        return checkingAccountService.createDefault(checkingAccountDTO);
    }

    @PostMapping
    @RequestMapping("/create-custom")
    public CheckingAccount createCustom(@RequestBody @Valid CheckingAccountDTO checkingAccountDTO) {
        return checkingAccountService.createCustom(checkingAccountDTO);
    }
}
