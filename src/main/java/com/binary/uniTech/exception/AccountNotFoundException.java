package com.binary.uniTech.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends GenericException{

    public AccountNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), message);
    }
}
