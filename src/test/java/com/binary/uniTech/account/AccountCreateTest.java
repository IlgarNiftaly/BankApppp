package com.binary.uniTech.account;


import com.binary.uniTech.entity.Account;
import com.binary.uniTech.exception.AccountConflictException;
import com.binary.uniTech.mapper.AccountMapper;
import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountCreateRequest;
import com.binary.uniTech.response.account.AccountCreateResponse;
import com.binary.uniTech.service.account.AccountCreateService;
import com.binary.uniTech.util.AccountUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountCreateTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountCreateService createService;

    @Test
    void testCreate_whenAccountCreateRequest_shouldReturnAccountCreateResponse(){

        AccountCreateRequest createRequest = AccountUtil.createRequest();
        AccountCreateResponse createResponse = AccountUtil.createResponse();
        Account account = AccountUtil.account();

        when(accountMapper.requestToEntity(createRequest)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(null);
        when(accountMapper.createToResponse(account)).thenReturn(createResponse);

        AccountCreateResponse checkData = createService.create(createRequest);
        Assertions.assertNotNull(checkData);
        Assertions.assertEquals(checkData.getAccountNumber(), createResponse.getAccountNumber());

        verify(accountRepository).findByAccountNumber(createRequest.getAccountNumber());
        verify(accountMapper).requestToEntity(createRequest);
        verify(accountRepository).save(account);
        verify(accountMapper).createToResponse(account);
    }

    @Test
    void testCheckAccountWithAccountNumber_whenAccountNumber_shouldReturnVoid(){

        AccountCreateRequest createRequest = AccountUtil.createRequest();
        Account account = AccountUtil.account();

        when(accountRepository.findByAccountNumber("4169738873966802")).thenReturn(account);

        Assertions.assertThrows(AccountConflictException.class, () -> createService.create(createRequest));

    }
}
