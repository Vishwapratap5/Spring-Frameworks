package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Service.CardTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cards/me")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class CardTransactionController {

    @Autowired
    private final CardTransactionService cardTransactionService;

    @PutMapping("/{cardId}/pay")
    public ResponseEntity<TransactionResponseDTO> payByCard(
            @PathVariable Long cardId,
            @RequestParam BigDecimal amount,
            @RequestHeader("Idempotency-key") String transactionRef
    ) {
        return ResponseEntity.ok(
                cardTransactionService.payByCard(cardId, amount, transactionRef)
        );
    }
}
