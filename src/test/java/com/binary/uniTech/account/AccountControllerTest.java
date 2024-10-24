package com.binary.uniTech.account;

import static com.binary.uniTech.enums.AccountStatus.ACTIVE;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Collections;

import com.binary.uniTech.controller.AccountController;
import com.binary.uniTech.request.account.*;
import com.binary.uniTech.response.account.AccountCreateResponse;
import com.binary.uniTech.response.account.AccountReadResponse;
import com.binary.uniTech.response.account.AccountUpdateResponse;
import com.binary.uniTech.service.account.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountCreateService createService;

    @Mock
    private AccountReadService readService;

    @Mock
    private AccountUpdateService updateService;

    @Mock
    private AccountDeleteService deleteService;

    @Mock
    private AccountTransferService transferService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void testCreate_withAccountCreateRequest_shouldReturnResponseEntityAccountCreateResponse() throws Exception {
        AccountCreateRequest request = new AccountCreateRequest();
        request.setAccountNumber("123456789");

        AccountCreateResponse response = new AccountCreateResponse();
        response.setAccountNumber("123456789");

        when(createService.create(any(AccountCreateRequest.class))).thenReturn(response);

        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"accountNumber\": \"123456789\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value("123456789"));
    }

    @Test
    public void testReadAll() throws Exception {
        AccountReadResponse account = new AccountReadResponse();
        account.setAccountNumber("123456789");

        when(readService.readAll()).thenReturn(Collections.singletonList(account));

        mockMvc.perform(get("/account/readAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber").value("123456789"));
    }

    @Test
    public void testReadByAccountNumber() throws Exception {
        AccountReadRequest request = new AccountReadRequest();
        request.setAccountNumber("123456789");

        AccountReadResponse response = new AccountReadResponse();
        response.setAccountNumber("123456789");

        when(readService.readByAccountNumber(any(AccountReadRequest.class))).thenReturn(response);

        mockMvc.perform(post("/account/readByAccountNumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"accountNumber\": \"123456789\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("123456789"));
    }

    @Test
    public void testReadByFkUserId() throws Exception {
        AccountReadRequest request = new AccountReadRequest();
        request.setFkUserId(1L);

        AccountReadResponse response = new AccountReadResponse();
        response.setFkUserId(1L);

        when(readService.readByFkUserId(any(AccountReadRequest.class))).thenReturn(response);

        mockMvc.perform(post("/account/readByFkUserId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"fkUserId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fkUserId").value(1));
    }

    @Test
    public void testReadByStatus() throws Exception {
        AccountReadRequest request = new AccountReadRequest();
        request.setStatus(ACTIVE);

        AccountReadResponse response = new AccountReadResponse();
        response.setStatus(ACTIVE);

        when(readService.readByStatus(any(AccountReadRequest.class))).thenReturn(response);

        mockMvc.perform(post("/account/readByStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"status\": \"ACTIVE\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        AccountUpdateRequest request = new AccountUpdateRequest();
        request.setId(1L);
        request.setAccountNumber("123456789");
        request.setBalance(BigDecimal.valueOf(1000.0));
        request.setFkUserId(2L);
        request.setStatus(ACTIVE);

        AccountUpdateResponse response = new AccountUpdateResponse();
        response.setAccountNumber("123456789");

        when(updateService.update(any(AccountUpdateRequest.class))).thenReturn(response);

        mockMvc.perform(post("/account/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"accountNumber\": \"123456789\", \"balance\": 1000.0, \"fkUserId\": 2, \"status\": \"ACTIVE\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value("123456789"));
    }

    @Test
    public void testDeleteAllAccounts() throws Exception {
        mockMvc.perform(post("/account/deleteAll"))
                .andExpect(status().isOk())
                .andExpect(content().string("All account is deleted"));

        verify(deleteService, times(1)).deleteAll();
    }

    @Test
    public void testDeleteByAccountNumber() throws Exception {
        AccountDeleteRequest request = new AccountDeleteRequest();
        request.setAccountNumber("123456789");

        mockMvc.perform(post("/account/deleteByAccountNumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"accountNumber\": \"123456789\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account is deleted"));

        verify(deleteService, times(1)).deleteByAccountNumber(request);
    }

    @Test
    public void testTransferToAnyBankCard() throws Exception {
        AccountTransferRequest request = new AccountTransferRequest();
        request.setSenderAccountNumber("123456789");
        request.setRecipientAccountNumber("987654321");
        request.setSenderBalance(BigDecimal.valueOf(1000));

        mockMvc.perform(post("/account/transferToAnyBankCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"senderAccountNumber\": \"123456789\", \"recipientAccountNumber\": \"987654321\", \"senderBalance\": 1000 }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transfer accepted"));

        verify(transferService, times(1)).transferToAnyBankCard(request);
    }
}
