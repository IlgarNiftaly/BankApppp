package com.binary.uniTech.exception;

import org.springframework.http.HttpStatus;

public class UserConflictException extends GenericException{

    public UserConflictException(String message){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), message);
    }
}
