package com.guru.springsecurity_learning.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MyLoans")
public class LoansController {
    @GetMapping("/details")
    public ResponseEntity<String> getLoanDetails() {
        String msg="Loans details";
        return new  ResponseEntity<>(msg, HttpStatus.OK);
    }
}
