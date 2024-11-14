package com.simplebank.simplebankapp.persistence.repository;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByUserUserId(Long userId);

    Optional<Account> findAccountByAccountId(Long accountId);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    List<Account> findByUser(User user);
}
