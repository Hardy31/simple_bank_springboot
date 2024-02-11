package com.bankdone.simple_bank_springboot.business.exeption;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){super(message);}
}
