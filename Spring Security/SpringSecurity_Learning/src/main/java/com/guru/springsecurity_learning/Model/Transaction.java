package com.guru.springsecurity_learning.Model;

import com.guru.springsecurity_learning.Enums.TransactionStatus;
import com.guru.springsecurity_learning.Enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private BigDecimal balanceBefore;

    @Column(nullable = false)
    private BigDecimal balanceAfter;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(nullable = false, unique = true, updatable = false)
    private String transactionRef;

    @Column(nullable = false)
    @NotNull
    private BigDecimal transactionAmount;

}

// NOTE:
// This Transaction entity represents a ledger entry for a *single account*.
// It intentionally does NOT store counterparty information.
//
// Reason:
// - Not all transactions have an internal counterparty (e.g. cash deposit,
//   withdrawal, interest credit, fees).
// - Real banking systems separate immutable ledger records from business context.
// - Counterparty / transfer details are modeled separately (e.g. via a Transfer
//   or TransactionMetadata entity) when needed.
//
// This keeps the ledger clean, auditable, and extensible.