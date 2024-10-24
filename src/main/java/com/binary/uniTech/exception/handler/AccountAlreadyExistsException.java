package com.binary.uniTech.exception.handler;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String code, String message){
        super(message);
    }
}
