package com.ironhack.ivanbank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    private Long originId;
    private Long destinationId;
    private BigDecimal amount;
    private String concept;
}
