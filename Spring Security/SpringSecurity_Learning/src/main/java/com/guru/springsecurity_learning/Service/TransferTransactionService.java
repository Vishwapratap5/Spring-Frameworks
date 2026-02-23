package com.guru.springsecurity_learning.Service;

import java.math.BigDecimal;

public interface TransferTransactionService {
    void transfer(
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount,
            String idempotencyKey
    );
}
