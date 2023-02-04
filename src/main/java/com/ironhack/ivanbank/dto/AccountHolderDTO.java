package com.ironhack.ivanbank.dto;

import com.ironhack.ivanbank.model.utils.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountHolderDTO {

    @NotNull
    private String name;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private String passport;
    @NotNull
    private Address primaryAddress;
    private String mailingAddress;

    public AccountHolderDTO(String name, LocalDate dateOfBirth, String passport,Address primaryAddress, String mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.passport = passport;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
}
