package com.binary.uniTech.controller;


import com.binary.uniTech.request.account.*;
import com.binary.uniTech.response.account.AccountCreateResponse;
import com.binary.uniTech.response.account.AccountReadResponse;
import com.binary.uniTech.response.account.AccountUpdateResponse;
import com.binary.uniTech.service.account.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountCreateService createService;
    private final AccountReadService readService;
    private final AccountUpdateService updateService;
    private final AccountDeleteService deleteService;
    private final AccountTransferService transferService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountCreateResponse> create(@RequestBody @Valid AccountCreateRequest createRequest){
        return ResponseEntity.ok(createService.create(createRequest));
    }

    @PostMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AccountReadResponse>> readAll(){
        return ResponseEntity.ok(readService.readAll());
    }

    @PostMapping("/readByAccountNumber")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccountReadResponse> readByAccountNumber(@RequestBody @Valid AccountReadRequest readRequest){
        return ResponseEntity.ok(readService.readByAccountNumber(readRequest));
    }

    @PostMapping("/readByFkUserId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccountReadResponse> readByFkUserId(@RequestBody @Valid AccountReadRequest readRequest){
        return ResponseEntity.ok(readService.readByFkUserId(readRequest));
    }

    @PostMapping("/readByStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccountReadResponse> readByStatus(@RequestBody @Valid AccountReadRequest readRequest){
        return ResponseEntity.ok(readService.readByStatus(readRequest));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountUpdateResponse> update(@RequestBody @Valid AccountUpdateRequest updateRequest){
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @PostMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteAll(){
        deleteService.deleteAll();
        return ResponseEntity.ok().body("All account is deleted");
    }

    @PostMapping("/deleteByAccountNumber")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteByAccountNumber(@RequestBody @Valid AccountDeleteRequest deleteRequest){
        deleteService.deleteByAccountNumber(deleteRequest);
        return ResponseEntity.ok().body("Account is deleted");
    }

    @PostMapping("/transferToAnyBankCard")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> transferToAnyBankCard(@RequestBody @Valid AccountTransferRequest transferRequest){
        transferService.transferToAnyBankCard(transferRequest);
        return ResponseEntity.ok().body("Transfer accepted");
    }

}
