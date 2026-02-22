package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account/me")
@PreAuthorize("hasRole('USER')")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllTransactions(accountId), HttpStatus.OK);
    }

    @PostMapping("/{accountId}/debit")
    public ResponseEntity<TransactionResponseDTO> debit(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount,
            @RequestHeader("Idempotency-Key") String transactionRef
    ) {
        return ResponseEntity.ok(
                transactionService.debit(accountId, amount, transactionRef)
        );
    }

    @PostMapping("/{accountId}/credit")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<TransactionResponseDTO> credit(@PathVariable("accountId") Long accountId, @RequestParam("amount") BigDecimal amount,@RequestHeader("Idempotency-Key") String transactionRef) {
        return new ResponseEntity<>(transactionService.credit(accountId,amount,transactionRef), HttpStatus.OK);
    }

    @GetMapping("/transactions/{transactionRef}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDTO> getByTransactionRef(@PathVariable String transactionRef) {
        return ResponseEntity.ok(transactionService.getByTransactionRef(transactionRef));
    }


}
