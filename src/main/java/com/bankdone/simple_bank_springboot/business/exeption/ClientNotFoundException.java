package com.bankdone.simple_bank_springboot.business.exeption;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
