package com.binary.uniTech.controller;

import com.binary.uniTech.request.user.*;
import com.binary.uniTech.response.user.UserReadResponse;
import com.binary.uniTech.response.user.UserRegisterResponse;
import com.binary.uniTech.response.user.UserUpdateResponse;
import com.binary.uniTech.service.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRegisterService registerService;
    private final UserReadService readService;
    private final UserUpdateService updateService;
    private final UserDeleteService deleteService;
    private final UserLoginService loginService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest registerRequest){
        if(registerRequest.getEmailVerified() == null){
            registerRequest.setEmailVerified(false);
        }
        return ResponseEntity.ok(registerService.register(registerRequest));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public boolean login(@RequestBody @Valid UserLoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @PostMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserReadResponse>> readAll(){
        return ResponseEntity.ok(readService.readAll());
    }

    @PostMapping("/readById")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadResponse> readById(@RequestBody @Valid UserReadRequest readRequest){
        return ResponseEntity.ok(readService.readById(readRequest));
    }

    @PostMapping("/readByUserName")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserReadResponse>> readByUserName(@RequestBody @Valid UserReadRequest readRequest){
        return ResponseEntity.ok(readService.readByUserName(readRequest));
    }

    @PostMapping("/readByUserPin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadResponse> readByUserPin(@RequestBody @Valid UserReadRequest readRequest){
        return ResponseEntity.ok(readService.readByUserPin(readRequest));
    }

    @PostMapping("/readByEmail")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadResponse> readByEmail(@RequestBody @Valid UserReadRequest readRequest){
        return ResponseEntity.ok(readService.readByEmail(readRequest));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserUpdateResponse> update(@RequestBody @Valid UserUpdateRequest updateRequest){
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @PostMapping("deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteAll(){
        deleteService.deleteAll();
        return ResponseEntity.ok().body("all user is deleted");
    }

    @PostMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@RequestBody @Valid UserDeleteRequest deleteRequest){
        deleteService.delete(deleteRequest);
        return ResponseEntity.ok().body("user is deleted");
    }

    @PostMapping("deleteById")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteById(@RequestBody @Valid UserDeleteRequest deleteRequest){
        deleteService.deleteById(deleteRequest);
        return ResponseEntity.ok().body("user is deleted");
    }

    @PostMapping("deleteByUserPin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteByUserPin(@RequestBody @Valid UserDeleteRequest deleteRequest){
        deleteService.deleteByUserPin(deleteRequest);
        return ResponseEntity.ok().body("user is deleted");
    }




}


























































