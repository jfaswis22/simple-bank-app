package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;

import java.util.List;

public interface IAccountService {
    Account findAccountByAccountNumber(String accountNumber);
    List<Account> findAllAccount();
    Account createAccount(AccountDTO accountDTO);
    Account updateAccount(AccountDTO accountDTO);
    void deleteAccount(String accountNumber);
}
