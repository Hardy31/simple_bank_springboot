package com.bankdone.simple_bank_springboot.business.exeption;

public class AgreementNotFoundExeption extends RuntimeException{
    public AgreementNotFoundExeption(String messaage){ super(messaage);}
}
