package com.guru.springsecurity_learning.DTO.EmiDTOs;

import com.guru.springsecurity_learning.Enums.EmiStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmiResponseDTO {

    private Long emiId;
    private Integer installmentNumber;
    private BigDecimal emiAmount;
    private LocalDate dueDate;
    private EmiStatus status;
    private LocalDate paymentDate;
}