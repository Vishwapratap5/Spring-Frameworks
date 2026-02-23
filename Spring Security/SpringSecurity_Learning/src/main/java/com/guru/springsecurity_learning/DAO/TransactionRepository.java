package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Model.Transaction;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_IdOrderByTransactionDateDesc(Long accountId);

    Optional<Transaction> findByTransactionRef(String transactionRef);

    boolean existsByTransactionRef(String transactionRef);
}