package com.ironhack.ivanbank.model;

import com.ironhack.ivanbank.model.utils.Money;
import com.ironhack.ivanbank.utils.Iban;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Random;

@Data
@Entity
public class CheckingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    @ManyToOne
    private AccountHolder primaryOwner;
    @ManyToOne
    private AccountHolder secondaryOwner;
    private BigDecimal minimumBalance;
    private BigDecimal penaltyFee;
    private BigDecimal monthlyMaintenanceFee;
    @CreationTimestamp
    private Instant creationDate;
    private Boolean isActive;

    public CheckingAccount() {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.minimumBalance = BigDecimal.valueOf(250);
        this.penaltyFee = BigDecimal.valueOf(40);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.isActive = true;
    }

    //Normal checking account with one owner
    public CheckingAccount(AccountHolder primaryOwner) {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.primaryOwner = primaryOwner;
        this.minimumBalance = BigDecimal.valueOf(250);
        this.penaltyFee = BigDecimal.valueOf(40);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.isActive = true;
    }

    //Normal checking account with two owners
    public CheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = BigDecimal.valueOf(250);
        this.penaltyFee = BigDecimal.valueOf(40);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.isActive = true;
    }

    //Used in SavingAccount and StudentCheckingAccount with one owner
    public CheckingAccount(AccountHolder primaryOwner, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.primaryOwner = primaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = BigDecimal.valueOf(40);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.isActive = true;
    }

    //Used in SavingAccount and StudentCheckingAccount with two owner
    public CheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = BigDecimal.valueOf(40);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.isActive = true;
    }
}
