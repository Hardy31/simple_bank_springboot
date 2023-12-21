package com.bankdone.simple_bank_springboot.business.exeption;

import lombok.Generated;

@Generated
public class ErrorMessage {
    public static final String ACCOUNT_NOT_FOUND = "The account was not found by this id!";
    public static final String CLIENT_NOT_FOUND = "The client was not found by this id!";
    public static final String Manager_NOT_FOUND = "The manager was not found by this id!";
    public static final String TAX_CODE_EXISTS = "The client with tax code is exists!";
    public static final String PRODUCT_NOT_FOUND = "The product was not found by this id!";
    public static final String AGREEMENT_NOT_FOUND = "The agreement was not found by this id!";
    public static final String TRANSACTION_NOT_FOUND = "The transaction was not found by this id!";
    public static final String NEGATIVE_DATA = "The data can not been negative!";
    public static final String NEGATIVE_BALANCE = "The balance on the account is not enough!";
    public static final String CONVERSION_NOT_AVAILABLE
            = "The currency code on the balance is not equals sending account. Conversion is not available at this time.";
}
