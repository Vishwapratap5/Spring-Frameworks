package com.guru.springsecurity_learning.DTO.AccountDTOs;

import com.guru.springsecurity_learning.Enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountUpdateRequestDTO {

    private String branchAddress;

    private AccountType accountType;

    private Long userId;

}

