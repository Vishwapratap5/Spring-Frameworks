package com.guru.springsecurity_learning.DTO.AccountDTOs;

import com.guru.springsecurity_learning.Enums.AccountStatus;
import com.guru.springsecurity_learning.Enums.AccountType;
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

    private String accountNumber;

    private AccountType accountType;

    private AccountStatus accountStatus;

    private String branchCode;

    private Long customerId;   // reference, not object

    private LocalDateTime createdAt;
}

