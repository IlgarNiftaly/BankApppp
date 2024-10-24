package com.binary.uniTech.service.account;

import com.binary.uniTech.entity.Account;
import com.binary.uniTech.enums.AccountStatus;
import com.binary.uniTech.exception.AccountConflictException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.mapper.AccountMapper;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountUpdateRequest;
import com.binary.uniTech.response.account.AccountUpdateResponse;
import com.binary.uniTech.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountUpdateService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AuthenticationService authenticationService;

    public AccountUpdateResponse update(AccountUpdateRequest updateRequest){
        Account account = authenticationService.checkAccountWithId(updateRequest.getId());
        if(!authenticationService.checkAccountWithAccountNumber(updateRequest.getAccountNumber())){
            throw new AccountConflictException(ErrorMessage.ACCOUNTNUMBER_ALREADY_EXISTS);
        }

        if(Objects.nonNull(updateRequest.getAccountNumber())){
            account.setAccountNumber(updateRequest.getAccountNumber());
        }
        if(Objects.nonNull(updateRequest.getBalance())){
            account.setBalance(updateRequest.getBalance());
        }
        if(Objects.nonNull(updateRequest.getFkUserId())){
            account.setFkUserId(updateRequest.getFkUserId());
        }
        if(Objects.nonNull(updateRequest.getStatus())){
            account.setStatus(AccountStatus.valueOf(String.valueOf(updateRequest.getStatus())));
        }
        accountRepository.save(account);
        log.info("account updated {}", account);
        return accountMapper.updateToResponse(account);
    }
}
