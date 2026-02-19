package com.guru.springsecurity_learning.DTO.CustomerDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPasswordUpdateDTO {
    private String oldPassword;

    private String newPassword;
}
