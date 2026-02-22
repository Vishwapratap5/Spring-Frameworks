package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.CardDTO.CardCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.CardDTO.CardResponseDTO;
import com.guru.springsecurity_learning.Service.CardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/cards/admin/")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class CardAdminController {

    @Autowired
    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<CardResponseDTO> create(@RequestBody CardCreationRequestDTO card) {
        return new ResponseEntity<>(cardService.create(card), HttpStatus.CREATED);
    }

    @PostMapping("/{cardId}/block")
    public ResponseEntity<Void> blockCard(@PathVariable("cardId") Long cardId) {
        cardService.blockCard(cardId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{cardId}/unblock")
    public ResponseEntity<Void> unblockCard(@PathVariable("cardId") Long cardId) {
        cardService.unblockCard(cardId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{cardId}/close")
    public ResponseEntity<Void> closeCard(@PathVariable("cardId") Long cardId) {
        cardService.closeCard(cardId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
