package com.binary.uniTech.exception;

import org.springframework.http.HttpStatus;

public class AccountBalanceException extends GenericException{

    public AccountBalanceException(String message){
        super(HttpStatus.PAYMENT_REQUIRED, HttpStatus.PAYMENT_REQUIRED.value(), message);
    }
}
