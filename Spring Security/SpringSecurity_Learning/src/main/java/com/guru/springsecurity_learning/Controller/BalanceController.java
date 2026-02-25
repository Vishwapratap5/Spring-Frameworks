package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/myBalance")
@PreAuthorize("hasRole('USER')")
public class BalanceController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<String> getBalanceDetails(@PathVariable Long accountId) {
        String msg=accountService.getBalance(accountId);
        return new  ResponseEntity<>(msg, HttpStatus.OK);
    }
}
