package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;
import com.guru.springsecurity_learning.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@PreAuthorize("hasRole('USER')")
public class AccountsController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/me")
    public ResponseEntity<List<AccountResponseDTO>> myAccounts(){
        return new ResponseEntity<>(accountService.myAccounts(),HttpStatus.OK);
    }

    @GetMapping("/me/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable("accountId") Long accountId){
        return new ResponseEntity<>(accountService.getAccountById(accountId),HttpStatus.OK);
    }


}
