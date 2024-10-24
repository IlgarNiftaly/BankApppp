package com.binary.uniTech.account;


import com.binary.uniTech.entity.Account;
import com.binary.uniTech.mapper.AccountMapper;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountReadRequest;
import com.binary.uniTech.response.account.AccountReadResponse;
import com.binary.uniTech.service.account.AccountReadService;
import com.binary.uniTech.service.authentication.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountReadTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AccountReadService readService;


    @Test
    void testReadAll_shouldReturnListAccountReadResponse(){

        List<Account> accounts = Collections.singletonList(new Account());
        when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountReadResponse> expectedResponse = Collections.singletonList(new AccountReadResponse());
        when(accountMapper.listEntityToListResponse(accounts)).thenReturn(expectedResponse);

        List<AccountReadResponse> actualResponse = readService.readAll();

        assertEquals(expectedResponse.size(), actualResponse.size());
    }

    @Test
    public void testReadByAccountNumber_whenAccountReadRequest_shouldReturnAccountReadResponse() {

        AccountReadRequest readRequest = new AccountReadRequest();
        readRequest.setAccountNumber("4169738873966802");

        when(authenticationService.checkAccountWithAccountNumber(anyString())).thenReturn(false);

        Account account = new Account();
        when(accountRepository.findByAccountNumber(readRequest.getAccountNumber())).thenReturn(account);

        verifyNoInteractions(accountMapper);

        AccountReadResponse response = readService.readByAccountNumber(readRequest);

        assertNotNull(response);
        verify(accountMapper, times(1)).readToResponse(account);
    }
}
