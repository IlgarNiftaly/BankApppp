package com.binary.uniTech.account;

import com.binary.uniTech.entity.Account;
import com.binary.uniTech.enums.AccountStatus;
import com.binary.uniTech.exception.AccountConflictException;
import com.binary.uniTech.mapper.AccountMapper;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountUpdateRequest;
import com.binary.uniTech.response.account.AccountUpdateResponse;
import com.binary.uniTech.service.account.AccountUpdateService;
import com.binary.uniTech.service.authentication.AuthenticationService;
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
public class AccountUpdateTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AccountUpdateService accountUpdateService;

    @Test
    public void testUpdate_withAccountUpdateRequest_shouldReturnAccountUpdateResponse() {
        // Mock data
        AccountUpdateRequest request = new AccountUpdateRequest();
        request.setId(1L);
        request.setAccountNumber("4169738873966802");
        request.setBalance(BigDecimal.valueOf(1000.0));
        request.setFkUserId(2L);
        request.setStatus(ACTIVE);

        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("4169738873966802");
        account.setBalance(BigDecimal.valueOf(500.0));
        account.setFkUserId(1L);
        account.setStatus(ACTIVE);

        // Mock behaviors
        when(authenticationService.checkAccountWithId(1L)).thenReturn(account);
        when(authenticationService.checkAccountWithAccountNumber("4169738873966802")).thenReturn(false);
        when(accountRepository.save(account)).thenReturn(account);

        // Method invocation
        AccountUpdateResponse response = accountUpdateService.update(request);

        // Verification
        assertEquals("4169738873966802", response.getAccountNumber());
        assertEquals(BigDecimal.valueOf(1000.0), response.getBalance());
        assertEquals(2L, response.getFkUserId());
        assertEquals(ACTIVE, response.getStatus());
    }

    @Test
    public void testUpdateWithExistingAccountNumber_withAccountUpdateRequest_shouldReturnException() {

        AccountUpdateRequest request = new AccountUpdateRequest();
        request.setId(1L);
        request.setAccountNumber("123456789");
        request.setBalance(BigDecimal.valueOf(1000.0));
        request.setFkUserId(2L);
        request.setStatus(ACTIVE);

        Account mockAccount = new Account();
        mockAccount.setId(1L);
        mockAccount.setAccountNumber("123456789");
        mockAccount.setBalance(BigDecimal.valueOf(500.0));
        mockAccount.setFkUserId(1L);
        mockAccount.setStatus(ACTIVE);

        when(authenticationService.checkAccountWithId(1L)).thenReturn(mockAccount);
        when(authenticationService.checkAccountWithAccountNumber("123456789")).thenReturn(true);

        // Verification
        assertThrows(AccountConflictException.class, () -> {
            accountUpdateService.update(request);
        });
    }
}
