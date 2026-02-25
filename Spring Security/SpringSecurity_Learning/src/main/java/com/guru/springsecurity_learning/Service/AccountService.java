package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountListResponseDTO;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;

public interface AccountService {
    AccountResponseDTO createAccount(AccountCreationRequestDTO account);

    void freezeAccount(Long accountId);

    void  unfreezeAccount(Long accountId);

    void closeAccount(Long accountId);

    AccountListResponseDTO myAccounts(int page, int size, String sortBy, String direction);

    AccountResponseDTO getAccountById(Long accountId);

    String getBalance(Long accountId);
}
