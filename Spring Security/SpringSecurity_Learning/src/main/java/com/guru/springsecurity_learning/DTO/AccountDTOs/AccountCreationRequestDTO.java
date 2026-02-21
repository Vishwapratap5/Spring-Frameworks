package com.guru.springsecurity_learning.DTO.AccountDTOs;

import com.guru.springsecurity_learning.Enums.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class  AccountCreationRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Account type is required")
    private AccountType accountType; // SAVINGS, CURRENT

    @NotNull(message = "Initial deposit is required")
    @PositiveOrZero(message = "Initial deposit cannot be negative")
    private BigDecimal initialDeposit;
}