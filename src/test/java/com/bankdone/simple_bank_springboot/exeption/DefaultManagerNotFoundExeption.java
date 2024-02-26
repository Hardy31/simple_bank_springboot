package com.bankdone.simple_bank_springboot.exeption;

import java.io.IOException;

public class DefaultManagerNotFoundExeption extends Exception {
    public DefaultManagerNotFoundExeption (String message) {
        super(message);
    }
}
