package com.guru.springsecurity_learning.DTO.LoanDTOs;

import com.guru.springsecurity_learning.Enums.LoanType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoanCreationRequestDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long repaymentAccountId;

    @NotNull
    private LoanType loanType;

    @NotNull
    @Positive
    private BigDecimal principalAmount;

    @NotNull
    @Positive
    private BigDecimal interestRate; // yearly %

    @NotNull
    @Min(1)
    private Integer tenureMonths;

    @NotNull
    private LocalDate startDate;
}

