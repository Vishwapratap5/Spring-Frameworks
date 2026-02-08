package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.CustomerRepo;
import com.guru.springsecurity_learning.Model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepo  customerRepo;

    public Customer register(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        Customer registeredCustomer= customerRepo.save(customer);
        if(registeredCustomer.getId() == null) {
            throw new IllegalStateException("User not persisted");
        }
        return registeredCustomer;
    }
}
