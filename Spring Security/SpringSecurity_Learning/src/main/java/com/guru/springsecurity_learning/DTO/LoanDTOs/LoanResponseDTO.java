package com.guru.springsecurity_learning.DTO.LoanDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponseDTO {

    private Long loanId;

    private String loanType;

    private LocalDate startDate;

    private Long totalLoanAmount;

    private Long totalLoanAmountPaid;

    private Long outstandingAmount;

    private LocalDateTime createdAt;
}
