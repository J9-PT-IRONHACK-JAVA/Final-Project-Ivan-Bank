package com.ironhack.ivanbank.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public class StudentCheckingAccount extends CheckingAccount {

    //
    public StudentCheckingAccount(AccountHolder primaryOwner) throws NoSuchAlgorithmException {
        super(primaryOwner, BigDecimal.valueOf(0), BigDecimal.valueOf(0));
    }

    public StudentCheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner) throws NoSuchAlgorithmException {
        super(primaryOwner, secondaryOwner, BigDecimal.valueOf(0), BigDecimal.valueOf(0));
    }

}
