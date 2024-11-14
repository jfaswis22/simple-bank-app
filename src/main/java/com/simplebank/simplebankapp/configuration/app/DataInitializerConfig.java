package com.simplebank.simplebankapp.configuration.app;

import com.simplebank.simplebankapp.persistence.entity.Account;
import com.simplebank.simplebankapp.persistence.entity.Role;
import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.persistence.repository.AccountRepository;
import com.simplebank.simplebankapp.persistence.repository.RoleRepository;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.util.enums.AccountType;
import com.simplebank.simplebankapp.util.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializerConfig implements CommandLineRunner {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        roleRepository.save(Role.builder()
                        .updatedAt(LocalDateTime.now())
                        .roleName("user")
                        .description("Permisos de usuario")
                        .createdAt(LocalDateTime.now())
                        .build());
        roleRepository.save(Role.builder()
                .updatedAt(LocalDateTime.now())
                .roleName("admin")
                .description("Permisos de administrador")
                .createdAt(LocalDateTime.now())
                .build());

        userRepository.save(User.builder()
                        .role(roleRepository.findByRoleName("user").get())
                        .dni("21370671C")
                        .name("Jhon")
                        .email("jhon@gmail.com")
                        .lastName("Aristizabal")
                        .password(passwordEncoder.encode("C8211083k@"))
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                .build());
        userRepository.save(User.builder()
                .role(roleRepository.findByRoleName("admin").get())
                .dni("11223344X")
                .name("Alice")
                .email("alice@example.com")
                .lastName("González")
                .password(passwordEncoder.encode("A1ice@dminPass"))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build());

        userRepository.save(User.builder()
                .role(roleRepository.findByRoleName("user").get())
                .dni("33445566Y")
                .name("Carlos")
                .email("carlos@example.com")
                .lastName("Ramírez")
                .password(passwordEncoder.encode("C4rl0sUserP@ss"))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build());

        userRepository.save(User.builder()
                .role(roleRepository.findByRoleName("user").get())
                .dni("55667788Z")
                .name("Laura")
                .email("laura@example.com")
                .lastName("Martínez")
                .password(passwordEncoder.encode("L@ur4Pass#2023"))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build());

        userRepository.save(User.builder()
                .role(roleRepository.findByRoleName("user").get())
                .dni("99001122W")
                .name("Miguel")
                .email("miguel@example.com")
                .lastName("Fernández")
                .password(passwordEncoder.encode("Miguel$M@nager"))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build());

        userRepository.save(User.builder()
                .role(roleRepository.findByRoleName("user").get())
                .dni("77889900V")
                .name("Sofía")
                .email("sofia@example.com")
                .lastName("López")
                .password(passwordEncoder.encode("Sof1a!User"))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                        .user(userRepository.findByEmail("sofia@example.com").get())
                        .currency("USD")
                        .updatedAt(LocalDateTime.now())
                        .status(Status.ACTIVE)
                        .balance(BigDecimal.valueOf(0))
                        .accountNumber(generateUniqueAccountNumber())
                        .accountType(AccountType.CHECKING)
                        .createdAt(LocalDateTime.now())
                        .build());
        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("alice@example.com").get())
                .currency("USD")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(2500.00))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.SAVINGS)
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("carlos@example.com").get())
                .currency("EUR")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(1500.00))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.CHECKING)
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("laura@example.com").get())
                .currency("USD")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(3000.00))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.SAVINGS)
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("miguel@example.com").get())
                .currency("GBP")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(500.00))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.CHECKING)
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("sofia@example.com").get())
                .currency("USD")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(0))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.SAVINGS)
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("sofia@example.com").get())
                .currency("USD")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(0))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.SAVINGS)
                .createdAt(LocalDateTime.now())
                .build());

        accountRepository.save(Account.builder()
                .user(userRepository.findByEmail("sofia@example.com").get())
                .currency("USD")
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .balance(BigDecimal.valueOf(0))
                .accountNumber(generateUniqueAccountNumber())
                .accountType(AccountType.SAVINGS)
                .createdAt(LocalDateTime.now())
                .build());
    }

    private String generateUniqueAccountNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 24; i++) {
            accountNumber.append(characters.charAt(random.nextInt(characters.length())));
        }
        return accountNumber.toString();
    }
}
