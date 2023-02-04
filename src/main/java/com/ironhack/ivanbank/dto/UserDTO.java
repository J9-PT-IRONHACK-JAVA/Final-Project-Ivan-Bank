package com.ironhack.ivanbank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull
    private String password;
}
