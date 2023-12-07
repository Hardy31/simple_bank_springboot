package com.bankdone.simple_bank_springboot.business.exeption;

/**
 * @пакет: simple_bank_springboot
 * @автор: alex
 * @от :    07.12.2023
 */
public class NegativeBalanceThrowException extends RuntimeException {
    public NegativeBalanceThrowException(String message){
        super(message);
    }
}
