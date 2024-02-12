package com.bankdone.simple_bank_springboot.business.exeption;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String messaage){ super(messaage);}
}
