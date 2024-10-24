package com.binary.uniTech.exception;

import org.springframework.http.HttpStatus;

public class AccountConflictException extends GenericException{

    public AccountConflictException(String message){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), message);
    }
}
