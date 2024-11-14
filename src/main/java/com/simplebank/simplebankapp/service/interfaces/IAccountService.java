package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.persistence.entity.Transaction;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;
import com.simplebank.simplebankapp.presentation.dto.TransferDTO;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    Account findAccountById(Long accountId);
    List<Account> findAllAccount(Authentication authentication);
    Account createAccount(AccountDTO accountDTO, Authentication authentication);
    void deleteAccount(Long accountId);
    BigDecimal showBalance(Long id, Authentication authentication);
    void transfer(TransferDTO transferDTO, Authentication authentication);

    List<Transaction> showTransactions(Authentication authentication);
}
