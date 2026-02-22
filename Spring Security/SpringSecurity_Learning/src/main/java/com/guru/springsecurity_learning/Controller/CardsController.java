package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.CardDTO.CardResponseDTO;
import com.guru.springsecurity_learning.Service.CardService;
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
@RequestMapping("/cards/me")
@PreAuthorize("hasRole('USER')")
public class CardsController {

    @Autowired
    private CardService cardService;

    @GetMapping("/details")
    public ResponseEntity<List<CardResponseDTO>> getCards() {

        return new  ResponseEntity<>(cardService.getCards(), HttpStatus.OK);
    }


    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponseDTO> getMyCard( @PathVariable Long cardId ) {
        return ResponseEntity.ok(
                cardService.getMyCard(cardId)
        );
    }
}
