package com.binary.uniTech.service.account;


import com.binary.uniTech.entity.Account;
import com.binary.uniTech.enums.AccountStatus;
import com.binary.uniTech.exception.AccountBalanceException;
import com.binary.uniTech.exception.AccountConflictException;
import com.binary.uniTech.exception.AccountNotFoundException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountTransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.binary.uniTech.enums.AccountStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class AccountTransferService {
    private final AccountRepository accountRepository;

    public void transferToAnyBankCard(AccountTransferRequest transferRequest){
        Account senderAccount = accountRepository.findByAccountNumber(transferRequest.getSenderAccountNumber());
        Account recipientAccount = accountRepository.findByAccountNumber(transferRequest.getRecipientAccountNumber());
        if(senderAccount == null){
            throw new AccountNotFoundException(ErrorMessage.SENDER_NOT_FOUND);
        }
        if(recipientAccount == null){
            throw new AccountNotFoundException(ErrorMessage.RECIPIENT_NOT_FOUND);
        }
        if(senderAccount.getAccountNumber().equals(recipientAccount.getAccountNumber())){
            throw new AccountConflictException(ErrorMessage.ACCOUNT_IDENTICAL);
        }
        if(!recipientAccount.getStatus().equals(ACTIVE)){
            throw new AccountConflictException(ErrorMessage.ACCOUNT_DISABLE);
        }

        if(senderAccount.getBalance().compareTo(transferRequest.getSenderBalance()) < 0){
            throw new AccountBalanceException(ErrorMessage.INSUFFICIENT_FUNDS);
        }
        senderAccount.setBalance(senderAccount.getBalance().subtract(transferRequest.getSenderBalance()));
        recipientAccount.setBalance(recipientAccount.getBalance().add(transferRequest.getSenderBalance()));

        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);
    }

}
