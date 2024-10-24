package com.binary.uniTech.service.account;


import com.binary.uniTech.entity.Account;
//import com.binary.uniTech.enums.Status;
import com.binary.uniTech.exception.AccountConflictException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.mapper.AccountMapper;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountCreateRequest;
import com.binary.uniTech.response.account.AccountCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountCreateService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountCreateResponse create(AccountCreateRequest createRequest){
        checkAccount(createRequest);
        Account account = accountMapper.requestToEntity(createRequest);
        accountRepository.save(account);
        return accountMapper.createToResponse(account);
    }

    private void checkAccount(AccountCreateRequest createRequest){
        checkAccountWithAccountNumber(createRequest.getAccountNumber());
    }
    private void checkAccountWithAccountNumber(String accountNumber){
        if(Objects.nonNull(accountRepository.findByAccountNumber(accountNumber))){
            throw new AccountConflictException(ErrorMessage.ACCOUNTNUMBER_ALREADY_EXISTS);
        }
    }

}
