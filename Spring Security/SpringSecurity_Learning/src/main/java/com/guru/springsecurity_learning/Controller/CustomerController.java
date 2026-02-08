package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer){
        Customer customer1=customerService.register(customer);
        return new  ResponseEntity<>(customer1, HttpStatus.OK);
    }
}
