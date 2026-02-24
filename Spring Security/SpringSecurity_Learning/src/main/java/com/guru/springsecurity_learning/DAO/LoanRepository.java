package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomerId(Long customerId);

    List<Loan> findByCustomer(Customer curreCustomer);
}