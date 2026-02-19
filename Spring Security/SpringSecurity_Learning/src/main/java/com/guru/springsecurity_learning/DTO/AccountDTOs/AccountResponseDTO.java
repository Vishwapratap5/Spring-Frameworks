package com.guru.springsecurity_learning.DTO.AccountDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;   // reference, not object

    private LocalDateTime createdAt;
}

