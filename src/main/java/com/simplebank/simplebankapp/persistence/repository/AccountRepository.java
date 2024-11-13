package com.simplebank.simplebankapp.persistence.repository;

import com.simplebank.simplebankapp.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
