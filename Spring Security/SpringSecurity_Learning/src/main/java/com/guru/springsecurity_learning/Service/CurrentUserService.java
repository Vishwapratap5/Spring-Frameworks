package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final CustomerRepository customerRepository;

    public CurrentUserService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCurrentCustomer() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user");
        }

        return customerRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public Long getCurrentCustomerId() {
        return getCurrentCustomer().getCustomerId();
    }
}
