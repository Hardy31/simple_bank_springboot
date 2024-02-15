package com.bankdone.simple_bank_springboot.business.exeption;

public class TransactionNotFoundExceptio extends RuntimeException{
    public TransactionNotFoundExceptio(String message){super(message);}
}
