package com.binary.uniTech.util;

import com.binary.uniTech.entity.Account;
import com.binary.uniTech.request.account.AccountCreateRequest;
import com.binary.uniTech.request.account.AccountDeleteRequest;
import com.binary.uniTech.response.account.AccountCreateResponse;

import java.math.BigDecimal;

import static com.binary.uniTech.enums.AccountStatus.ACTIVE;

public class AccountUtil {

    private AccountUtil(){

    }

    public static AccountCreateRequest createRequest(){
        AccountCreateRequest createRequest = new AccountCreateRequest();
        createRequest.setAccountNumber("4169738873966802");
        createRequest.setBalance(BigDecimal.valueOf(1000));
        createRequest.setFkUserId(1L);
        createRequest.setStatus(ACTIVE);

        return createRequest;
    }

    public static AccountCreateResponse createResponse(){
        AccountCreateResponse createResponse = new AccountCreateResponse();
        createResponse.setAccountNumber("4169738873966802");
        createResponse.setBalance(BigDecimal.valueOf(1000));
        createResponse.setFkUserId(1L);
        createResponse.setStatus(ACTIVE);

        return createResponse;
    }

    public static Account account(){
        Account account = new Account();
        account.setAccountNumber("4169738873966802");
        account.setBalance(BigDecimal.valueOf(1000));
        account.setFkUserId(1L);
        account.setStatus(ACTIVE);

        return account;
    }

    public static AccountDeleteRequest deleteRequest(){
        AccountDeleteRequest deleteRequest = new AccountDeleteRequest();
        deleteRequest.setAccountNumber("4169738873966802");

        return deleteRequest;
    }




}
