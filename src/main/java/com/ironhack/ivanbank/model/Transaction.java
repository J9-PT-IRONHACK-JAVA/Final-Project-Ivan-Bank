package com.ironhack.ivanbank.model;

import com.ironhack.ivanbank.model.utils.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Instant transactionDate;
    @OneToOne
    private CheckingAccount origin;
    private String originName;
    @OneToOne
    private CheckingAccount destination;
    private String destinationName;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    private BigDecimal amount;
    private String concept;
}
