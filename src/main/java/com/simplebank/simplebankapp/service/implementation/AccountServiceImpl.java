package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.persistence.entity.Transaction;
import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.persistence.repository.AccountRepository;
import com.simplebank.simplebankapp.persistence.repository.TransactionRepository;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.presentation.dto.AccountDTO;
import com.simplebank.simplebankapp.presentation.dto.TransferDTO;
import com.simplebank.simplebankapp.service.exception.AccessDeniedException;
import com.simplebank.simplebankapp.service.exception.AccountNotFoundException;
import com.simplebank.simplebankapp.service.exception.EmailNotFoundException;
import com.simplebank.simplebankapp.service.interfaces.IAccountService;
import com.simplebank.simplebankapp.util.enums.AccountStatus;
import com.simplebank.simplebankapp.util.enums.TransactionStatus;
import com.simplebank.simplebankapp.util.enums.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_CHARACTERS = 24;


    @Override
    public Account findAccountById(Long accountId) {
        if (accountRepository.findById(accountId).isEmpty()) {
            throw new AccountNotFoundException("The account with id " + accountId + " was not found");
        }

        return accountRepository.findById(accountId).get();
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
                .accountNumber(generateNumberAccount())
                .balance(BigDecimal.valueOf(0))
                .accountStatus(AccountStatus.ACTIVE)
                .updatedAt(LocalDateTime.now())
                .currency(accountDTO.currency())
                .user(userRepository.findByEmail(authentication.getName()).get())
                .build();

        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("The account with id " + accountId + " was not found"));

        accountRepository.delete(account);
    }

    @Override
    public BigDecimal showBalance(Long id, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EmailNotFoundException("The user with email " + authentication.getName() + " was not found"));

        return accountRepository.findAccountByAccountId(id).get().getBalance();
    }

    @Override
    @Transactional
    public void transfer(TransferDTO transferDTO, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EmailNotFoundException("The user with email " + authentication.getName() + " was not found"));

        Account fromAccount = accountRepository.findById(transferDTO.fromAccountId())
                .orElseThrow(() -> new AccountNotFoundException("The account with id " + transferDTO.fromAccountId() + " was not found"));

        Account toAccount = accountRepository.findAccountByAccountNumber(transferDTO.accountNumber())
                .orElseThrow(() -> new AccountNotFoundException("The account with account number " + transferDTO.accountNumber() + " was not found"));

        if (!fromAccount.getUser().getUserId().equals(user.getUserId())) {
            throw new AccessDeniedException("You do not have permission to perform this operation");
        }

        if (fromAccount.getBalance().compareTo(transferDTO.amount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds in the source account");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferDTO.amount()));
        toAccount.setBalance(toAccount.getBalance().add(transferDTO.amount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = Transaction.builder()
                .amount(transferDTO.amount())
                .transactionType(TransactionType.TRANSFER)
                .currency(fromAccount.getCurrency())
                .transactionDate(LocalDateTime.now())
                .status(TransactionStatus.COMPLETED)
                .description("Transfer from account " + fromAccount.getAccountNumber() + " to account " + toAccount.getAccountNumber())
                .fromAccountId(fromAccount)
                .toAccountId(toAccount)
                .build();

        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> showTransactions(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EmailNotFoundException("The user with email " + authentication.getName() + " was not found"));

        List<Account> userAccounts = accountRepository.findByUser(user);

        return transactionRepository.findByFromAccountIdInOrToAccountIdIn(userAccounts, userAccounts);
    }

    private String generateNumberAccount() {
        Random random = new Random();
        StringBuilder account = new StringBuilder(MAX_CHARACTERS);

        for (int i = 0; i < MAX_CHARACTERS; i++) {
            int index = random.nextInt(CHARACTERS.length());
            account.append(CHARACTERS.charAt(index));
        }

        return account.toString();
    }
}
