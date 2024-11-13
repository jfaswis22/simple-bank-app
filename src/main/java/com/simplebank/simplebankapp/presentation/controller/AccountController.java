package com.simplebank.simplebankapp.presentation.controller;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;
import com.simplebank.simplebankapp.service.interfaces.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final IAccountService accountService;

    @PostMapping(value = "/create")
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountDTO accountDTO,
                                                 Authentication authentication) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO, authentication), HttpStatus.CREATED);
    }
}
