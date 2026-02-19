package com.guru.springsecurity_learning.DTO.CustomerDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerRegisterRequestDTO {

    private String password;

    private String customerName;

    private String email;

    private String mobileNumber;

}
