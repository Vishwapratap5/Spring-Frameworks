package com.guru.springsecurity_learning.DTO.TransactionDTO;


import com.guru.springsecurity_learning.Enums.TransactionStatus;
import com.guru.springsecurity_learning.Enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {

    private String transactionRef;

    private TransactionType transactionType;

    private TransactionStatus status;

    private BigDecimal transactionAmount;

    private BigDecimal balanceAfter;

    private LocalDateTime transactionDate;
}
