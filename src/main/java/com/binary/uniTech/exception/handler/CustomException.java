package com.binary.uniTech.exception.handler;

import com.binary.uniTech.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class CustomException {

    @ExceptionHandler(AccountBalanceException.class)
    @ResponseStatus(PAYMENT_REQUIRED)
    public ProblemDetail handlerAccountBalanceException(Exception exception){
        log.info("handlerAccountBalanceException {}", exception.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerAccountNotFoundException(Exception exception){
        log.info("handlerAccountNotFoundException {}", exception.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(AccountConflictException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerAccountConflictException(Exception exception){
        log.info("handlerAccountConflictException {}", exception.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerUserNotFoundException(Exception exception){
        log.info("handlerUserNotFoundException {}", exception.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(UserConflictException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerUserConflictException(Exception exception){
        log.info("handlerUserConflictException {}", exception.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, exception.getMessage());
    }
}
