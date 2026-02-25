package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountListResponseDTO;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;
import com.guru.springsecurity_learning.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@PreAuthorize("hasRole('USER')")
public class AccountsController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/me")
    public ResponseEntity<AccountListResponseDTO> myAccounts(
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
            @RequestParam(name="direction",defaultValue = "desc") String direction
    ) {
        return ResponseEntity.ok(
                accountService.myAccounts(page, size, sortBy, direction)
        );
    }

    @GetMapping("/me/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable("accountId") Long accountId){
        return new ResponseEntity<>(accountService.getAccountById(accountId),HttpStatus.OK);
    }


}
