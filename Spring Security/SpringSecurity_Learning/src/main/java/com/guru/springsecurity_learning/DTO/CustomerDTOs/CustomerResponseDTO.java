package com.guru.springsecurity_learning.DTO.CustomerDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerResponseDTO {
    private Long customerId;

    private String customerName;

    private String email;

    private String mobileNumber;

    private Long accountNumber;

    private LocalDateTime createdAt;
}
