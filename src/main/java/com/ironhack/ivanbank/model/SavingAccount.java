package com.ironhack.ivanbank.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;


public class SavingAccount extends CheckingAccount {

    private BigDecimal interestRate;

    //Default parameters one owner
    public SavingAccount(AccountHolder primaryOwner) throws NoSuchAlgorithmException {
        super(primaryOwner, BigDecimal.valueOf(1000), BigDecimal.valueOf(0));
        this.interestRate = BigDecimal.valueOf(0.0025);
    }

    //Default parameters two owners
    public SavingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner) throws NoSuchAlgorithmException {
        super(primaryOwner, secondaryOwner,BigDecimal.valueOf(1000), BigDecimal.valueOf(0));
        this.interestRate = BigDecimal.valueOf(0.0025);
    }

    //Custom parameters one owner
    public SavingAccount(AccountHolder primaryOwner, BigDecimal minimumBalance, BigDecimal interestRate) throws NoSuchAlgorithmException {
        super(primaryOwner, minimumBalance, BigDecimal.valueOf(0));
        setInterestRate(interestRate);
    }

    //Custom parameters two owners
    public SavingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal interestRate) throws NoSuchAlgorithmException {
        super(primaryOwner, secondaryOwner, minimumBalance, BigDecimal.valueOf(0));
        setInterestRate(interestRate);
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.5)) >= 0){
            this.interestRate = BigDecimal.valueOf(0.5);
        } else if (interestRate.compareTo(BigDecimal.valueOf(0.0025)) <= 0) {
            this.interestRate = BigDecimal.valueOf(0.0025);
        } else {
            this.interestRate = interestRate;
        }
    }
}
