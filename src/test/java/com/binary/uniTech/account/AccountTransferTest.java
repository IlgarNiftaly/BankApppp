package com.binary.uniTech.account;


import com.binary.uniTech.entity.Account;
import com.binary.uniTech.exception.AccountBalanceException;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountTransferRequest;
import com.binary.uniTech.service.account.AccountTransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;

import static com.binary.uniTech.enums.AccountStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountTransferTest {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountTransferService accountTransferService;

    @Test
    public void testTransferToAnyBankCard() {

        AccountTransferRequest request = new AccountTransferRequest();
        request.setSenderAccountNumber("123456789");
        request.setRecipientAccountNumber("987654321");
        request.setSenderBalance(BigDecimal.valueOf(1000));

        Account senderAccount = new Account();
        senderAccount.setAccountNumber("123456789");
        senderAccount.setBalance(BigDecimal.valueOf(2000));
        senderAccount.setStatus(ACTIVE);

        Account recipientAccount = new Account();
        recipientAccount.setAccountNumber("987654321");
        recipientAccount.setBalance(BigDecimal.valueOf(5000));
        recipientAccount.setStatus(ACTIVE);

        // Mock behaviors
        when(accountRepository.findByAccountNumber("123456789")).thenReturn(senderAccount);
        when(accountRepository.findByAccountNumber("987654321")).thenReturn(recipientAccount);

        // Method invocation
        accountTransferService.transferToAnyBankCard(request);

        // Verification
        assertEquals(BigDecimal.valueOf(1000), senderAccount.getBalance());
        assertEquals(BigDecimal.valueOf(6000), recipientAccount.getBalance());
    }

    @Test
    public void testTransferWithInsufficientFunds() {
        // Mock data
        AccountTransferRequest request = new AccountTransferRequest();
        request.setSenderAccountNumber("123456789");
        request.setRecipientAccountNumber("987654321");
        request.setSenderBalance(BigDecimal.valueOf(5000));

        Account senderAccount = new Account();
        senderAccount.setAccountNumber("123456789");
        senderAccount.setBalance(BigDecimal.valueOf(2000));
        senderAccount.setStatus(ACTIVE);

        Account recipientAccount = new Account();
        recipientAccount.setAccountNumber("987654321");
        recipientAccount.setBalance(BigDecimal.valueOf(5000));
        recipientAccount.setStatus(ACTIVE);

        // Mock behaviors
        when(accountRepository.findByAccountNumber("123456789")).thenReturn(senderAccount);
        when(accountRepository.findByAccountNumber("987654321")).thenReturn(recipientAccount);

        // Verification
        assertThrows(AccountBalanceException.class, () -> {
            accountTransferService.transferToAnyBankCard(request);
        });
    }

}
