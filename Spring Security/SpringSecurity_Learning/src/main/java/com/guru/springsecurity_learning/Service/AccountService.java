package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(AccountCreationRequestDTO account);

    void freezeAccount(Long accountId);

    void  unfreezeAccount(Long accountId);

    void closeAccount(Long accountId);

    List<AccountResponseDTO> myAccounts();

    AccountResponseDTO getAccountById(Long accountId);
}
