package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.CardDTO.CardListResponseDTO;
import com.guru.springsecurity_learning.DTO.CardDTO.CardResponseDTO;
import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards/me")
@PreAuthorize("hasRole('USER')")
public class CardsController {

    @Autowired
    private CardService cardService;

    @GetMapping("/details")
    public ResponseEntity<CardListResponseDTO> getCards(@RequestParam(name="page",defaultValue = "0") int page,
                                                         @RequestParam(name="size",defaultValue = "10") int size,
                                                         @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                         @RequestParam(name="direction",defaultValue = "desc") String direction) {

        return new  ResponseEntity<>(cardService.getCards(page, size, sortBy, direction), HttpStatus.OK);
    }


    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponseDTO> getMyCard( @PathVariable Long cardId ) {
        return ResponseEntity.ok(
                cardService.getMyCard(cardId)
        );
    }

}
