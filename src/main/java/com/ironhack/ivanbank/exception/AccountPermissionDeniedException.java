package com.ironhack.ivanbank.exception;

public class AccountPermissionDeniedException extends Exception{

    public AccountPermissionDeniedException(String message) {
        super(message);
    }
}
