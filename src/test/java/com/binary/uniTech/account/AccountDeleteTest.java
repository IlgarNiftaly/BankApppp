package com.binary.uniTech.account;

import com.binary.uniTech.repository.AccountRepository;
import com.binary.uniTech.request.account.AccountDeleteRequest;
import com.binary.uniTech.service.account.AccountDeleteService;
import com.binary.uniTech.util.AccountUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountDeleteTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountDeleteService deleteService;

    @Test
    void testDeleteAll(){

        deleteService.deleteAll();

        verify(accountRepository, times(1)).deleteAll();

    }

    @Test
    void testDeleteByAccountNumber_whenAccountDeleteRequest_shouldReturnVoid(){

        AccountDeleteRequest deleteRequest = AccountUtil.deleteRequest();

        deleteService.deleteByAccountNumber(deleteRequest);

        verify(accountRepository, times(1)).deleteByAccountNumber(deleteRequest.getAccountNumber());
    }
}
