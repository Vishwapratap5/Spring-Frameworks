package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.CustomerRepository;
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
    private CustomerRepository customerRepository;

    public Customer register(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        Customer registeredCustomer= customerRepository.save(customer);
        if(registeredCustomer.getCustomerId() == null) {
            throw new IllegalStateException("User not persisted");
        }
        return registeredCustomer;
    }
}
