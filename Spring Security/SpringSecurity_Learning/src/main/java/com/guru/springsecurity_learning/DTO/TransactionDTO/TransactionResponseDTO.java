package com.guru.springsecurity_learning.DTO.TransactionDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponseDTO {
    private Long transactionId;
    private String transactionType;
    private Long transactionAmount;
    private LocalDateTime transactionDate;
}
