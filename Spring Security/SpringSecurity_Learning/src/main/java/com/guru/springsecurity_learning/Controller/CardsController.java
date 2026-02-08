package com.guru.springsecurity_learning.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myCards")
public class CardsController {

    @GetMapping("/details")
    public ResponseEntity<String> getCardsDetails() {
        String msg="Card details";
        return new  ResponseEntity<>(msg, HttpStatus.OK);
    }
}
