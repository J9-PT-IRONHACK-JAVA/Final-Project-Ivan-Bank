package com.ironhack.ivanbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
public class StudentCheckingAccount extends CheckingAccount{

    public StudentCheckingAccount(AccountHolder primaryOwner, BigDecimal penaltyFee) throws NoSuchAlgorithmException {
        super(primaryOwner, BigDecimal.valueOf(0), penaltyFee,BigDecimal.valueOf(0));
    }
}
