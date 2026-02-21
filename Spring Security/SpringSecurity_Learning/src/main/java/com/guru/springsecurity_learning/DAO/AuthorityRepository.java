package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.Authority;
import com.guru.springsecurity_learning.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    boolean existsByCustomerAndRole(Customer customer, Role role);

    void deleteByCustomerAndRole(Customer foundCustomer, Role role);

    long countByRole(Role role);
}