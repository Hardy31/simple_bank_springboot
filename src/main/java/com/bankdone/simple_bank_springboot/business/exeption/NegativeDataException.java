package com.bankdone.simple_bank_springboot.business.exeption;

import org.apache.logging.log4j.message.Message;

public class NegativeDataException extends RuntimeException {
    public NegativeDataException(String message){super(message);}
}
