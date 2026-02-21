package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;
import com.guru.springsecurity_learning.Service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/accounts")
@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
public class AccountAdminController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountCreationRequestDTO account) {

        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}/freeze")
    public ResponseEntity<Void> freezeAccount(@PathVariable("accountId") Long accountId) {
        accountService.freezeAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{accountId}/unfreeze")
    public ResponseEntity<Void> unfreezeAccount(@PathVariable("accountId") Long accountId) {
        accountService.unfreezeAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{accountId}/close")
    public ResponseEntity<Void> closeAccount(@PathVariable("accountId") Long accountId) {
        accountService.closeAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
