package com.bankdone.simple_bank_springboot.business.exeption;

public class ManagerNotFoundException extends RuntimeException {
    public ManagerNotFoundException (String message) {
        super(message);
    }
}
