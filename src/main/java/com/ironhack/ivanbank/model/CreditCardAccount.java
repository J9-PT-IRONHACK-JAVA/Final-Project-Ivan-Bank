package com.ironhack.ivanbank.model;

import com.ironhack.ivanbank.model.utils.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

@Data
@Entity
@NoArgsConstructor
public class CreditCardAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    @ManyToOne
    private AccountHolder primaryOwner;
    @ManyToOne
    private AccountHolder secondaryOwner;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal penaltyFee;

    public CreditCardAccount(AccountHolder primaryOwner) throws NoSuchAlgorithmException {
        this.balance = new Money();
        this.primaryOwner = primaryOwner;
        this.creditLimit = BigDecimal.valueOf(100);
        this.interestRate = BigDecimal.valueOf(0.2);
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public CreditCardAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner) throws NoSuchAlgorithmException {
        this.balance = new Money();
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creditLimit = BigDecimal.valueOf(100);
        this.interestRate = BigDecimal.valueOf(0.2);
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public CreditCardAccount(AccountHolder primaryOwner, BigDecimal creditLimit, BigDecimal interestRate) throws NoSuchAlgorithmException {
        this.balance = new Money();
        this.primaryOwner = primaryOwner;
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public CreditCardAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate) throws NoSuchAlgorithmException {
        this.balance = new Money();
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit.compareTo(BigDecimal.valueOf(100000)) >= 0) {
            this.creditLimit = BigDecimal.valueOf(100000);
        } else if (creditLimit.compareTo(BigDecimal.valueOf(100)) <= 0) {
            this.creditLimit = BigDecimal.valueOf(100);
        } else {
            this.creditLimit = creditLimit;
        }
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.2)) >= 0) {
            this.interestRate = BigDecimal.valueOf(0.2);
        } else if (interestRate.compareTo(BigDecimal.valueOf(0.1)) <= 0) {
            this.interestRate = BigDecimal.valueOf(0.1);
        } else {
            this.interestRate = interestRate;
        }
    }
}
