package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IAccountService {
    Account findAccountById(Long accountId);
    List<Account> findAllAccount(Authentication authentication);
    Account createAccount(AccountDTO accountDTO, Authentication authentication);
    void deleteAccount(Long accountId);
}
