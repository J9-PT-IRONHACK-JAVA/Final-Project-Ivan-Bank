package com.ironhack.ivanbank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CheckingAccountDTO {

    @NotNull
    Long primaryOwner;
    Long secondaryOwner;
    private BigDecimal minimumBalance;
    private BigDecimal penaltyFee;
    private BigDecimal monthlyMaintenanceFee;
}
