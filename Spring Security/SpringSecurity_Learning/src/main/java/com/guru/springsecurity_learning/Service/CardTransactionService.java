package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;

import java.math.BigDecimal;

public interface CardTransactionService {
    TransactionResponseDTO payByCard(Long cardId, BigDecimal amount,String transactionRef);
}
