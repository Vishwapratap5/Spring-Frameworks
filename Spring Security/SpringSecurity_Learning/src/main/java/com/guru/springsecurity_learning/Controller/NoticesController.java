package com.guru.springsecurity_learning.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notices")
public class NoticesController {
    @GetMapping("/details")
    public ResponseEntity<String> getNoticeDetails() {
        String msg="Notice Details";
        return new  ResponseEntity<>(msg, HttpStatus.OK);
    }
}
