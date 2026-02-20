package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;
import com.guru.springsecurity_learning.Exception.ResourceNotFoundException;
import com.guru.springsecurity_learning.Model.Account;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;


    public AccountResponseDTO getAccountDetailsById(long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("given resource not found..!"));
        return modelMapper.map(account, AccountResponseDTO.class);
    }
}
