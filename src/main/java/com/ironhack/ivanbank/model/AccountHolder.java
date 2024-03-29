package com.ironhack.ivanbank.model;

import com.ironhack.ivanbank.model.utils.Address;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String passport;

    @Embedded
    private Address primaryAddress;

    private String mailingAddress;

    public AccountHolder(String name, LocalDate dateOfBirth, String passport,Address primaryAddress, String mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.passport = passport;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
}
