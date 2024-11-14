package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.persistence.repository.AccountRepository;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;
import com.simplebank.simplebankapp.service.exception.EmailNotFoundException;
import com.simplebank.simplebankapp.service.interfaces.IAccountService;
import com.simplebank.simplebankapp.util.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_CHARACTERS = 24;
    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public List<Account> findAllAccount(Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            throw new EmailNotFoundException("The email " + authentication.getName() + " was not found");
        }

        Long id = userRepository.findByEmail(authentication.getName()).get().getUserId();

        return accountRepository.findAccountsByUserUserId(id);
    }

    @Override
    public Account createAccount(AccountDTO accountDTO, Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            throw new EmailNotFoundException("The email " + authentication.getName() + " was not found");
        }

        Account account = Account.builder()
                .accountType(accountDTO.accountType())
                .createdAt(LocalDateTime.now())
                .accountNumber(generarCuenta())
                .balance(BigDecimal.valueOf(0))
                .status(Status.ACTIVE)
                .updatedAt(LocalDateTime.now())
                .currency(accountDTO.currency())
                .user(userRepository.findByEmail(authentication.getName()).get())
                .build();
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public void deleteAccount(String accountNumber) {

    }

    private String generarCuenta() {
        Random random = new Random();
        StringBuilder cuenta = new StringBuilder(MAX_CHARACTERS);

        for (int i = 0; i < MAX_CHARACTERS; i++) {
            int index = random.nextInt(CHARACTERS.length());
            cuenta.append(CHARACTERS.charAt(index));
        }

        return cuenta.toString();
    }
}
