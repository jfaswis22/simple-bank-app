package com.simplebank.simplebankapp.persistence.entity;

import com.simplebank.simplebankapp.util.enums.TransactionStatus;
import com.simplebank.simplebankapp.util.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private LocalDateTime transactionDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccountId;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = true)
    private Account toAccountId;
}
