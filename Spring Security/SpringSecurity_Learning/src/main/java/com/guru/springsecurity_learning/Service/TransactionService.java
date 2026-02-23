package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import org.apache.coyote.BadRequestException;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getAllTransactions(Long accountId);

    TransactionResponseDTO debit(Long accountId, BigDecimal amount,String transactionRef) ;

    TransactionResponseDTO credit(Long accountId, BigDecimal amount,String transactionRef) ;

    TransactionResponseDTO getByTransactionRef(String transactionRef);

}
