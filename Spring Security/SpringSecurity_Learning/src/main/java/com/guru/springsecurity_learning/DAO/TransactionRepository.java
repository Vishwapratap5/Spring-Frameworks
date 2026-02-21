package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount_IdOrderByTransactionDateDesc(Long accountId);
}