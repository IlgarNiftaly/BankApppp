package com.binary.uniTech.exception;

import org.springframework.http.HttpStatus;


public class UserNotFoundException extends GenericException{

    public UserNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), message);
    }


}
