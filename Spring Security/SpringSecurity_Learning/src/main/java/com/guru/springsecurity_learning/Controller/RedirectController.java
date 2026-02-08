package com.guru.springsecurity_learning.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RedirectController {
    @GetMapping("/")
    public String index() {
        return "Welcome to Home Page";
    }
}
