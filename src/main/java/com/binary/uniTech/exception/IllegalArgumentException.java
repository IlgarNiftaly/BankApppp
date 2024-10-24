package com.binary.uniTech.exception;

import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends GenericException{

    public IllegalArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message);
    }
}
