package com.ironhack.ivanbank.utils;

import java.io.Serializable;
import java.util.Random;

public class Iban implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int IBAN_LENGTH = 22;
    private static final int CHECKSUM_LENGTH = 2;

    private String iban;

    public Iban() {
        this.iban = generateIban();
    }

    public String generateIban() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < IBAN_LENGTH - CHECKSUM_LENGTH; i++) {
            sb.append(rand.nextInt(10));
        }
        int checksum = 98 - (Integer.parseInt(sb.toString()) % 97);
        sb.insert(0, checksum < 10 ? "0" + checksum : "" + checksum);
        sb.insert(0, "ES");
        return sb.toString();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
