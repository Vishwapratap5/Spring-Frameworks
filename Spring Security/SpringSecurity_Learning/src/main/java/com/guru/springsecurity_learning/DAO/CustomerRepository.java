package com.guru.springsecurity_learning.DAO;


import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByCustomerName(String customerName);

    Optional<Customer> findByCustomerId(Long customerId);
}
