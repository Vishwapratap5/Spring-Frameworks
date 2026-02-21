package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getAllTransactions(Long accountId);
}
