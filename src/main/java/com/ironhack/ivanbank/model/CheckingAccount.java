package com.ironhack.ivanbank.model;

import com.ironhack.ivanbank.model.utils.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
public class CheckingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    private SecretKey secretKey;
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

    public CheckingAccount(AccountHolder primaryOwner, BigDecimal minimumBalance, BigDecimal penaltyFee, BigDecimal monthlyMaintenanceFee) throws NoSuchAlgorithmException {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.secretKey = KeyGenerator.getInstance("AES").generateKey();
        this.primaryOwner = primaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public CheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal penaltyFee, BigDecimal monthlyMaintenanceFee) throws NoSuchAlgorithmException {
        this.balance = new Money(BigDecimal.valueOf(0));
        this.secretKey = KeyGenerator.getInstance("AES").generateKey();
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }
}
