package com.bankdone.simple_bank_springboot.business.exeption;

/**
 * @пакет: simple_bank_springboot
 * @автор: alex
 * @от :    07.12.2023
 */
public class CodeCurrencyNotMatchingException extends RuntimeException{
    public CodeCurrencyNotMatchingException(String message){
        super(message);
    }
}
