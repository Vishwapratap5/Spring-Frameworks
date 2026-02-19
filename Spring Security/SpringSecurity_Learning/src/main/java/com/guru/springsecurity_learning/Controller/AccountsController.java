package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;
import com.guru.springsecurity_learning.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myAccount")
public class AccountsController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/details")
    public ResponseEntity<AccountResponseDTO> getAccountDetails(@RequestParam(value="id") long id) {
        AccountResponseDTO accountDetails = accountService.getAccountDetailsById(id);
        return new  ResponseEntity<>(accountDetails, HttpStatus.OK);
    }
}
