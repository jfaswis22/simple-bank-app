package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;
import com.simplebank.simplebankapp.service.interfaces.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public List<Account> findAllAccount() {
        return null;
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Account updateAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public void deleteAccount(String accountNumber) {

    }
}
