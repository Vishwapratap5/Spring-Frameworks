package com.guru.springsecurity_learning.DTO.CustomerDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginRequestDTO {

    private String email;

    private String password;
}
