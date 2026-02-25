package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Page<Loan> findByCustomerId(Long customerId, Pageable pageable);

    Page<Loan> findByCustomer(Customer curreCustomer);
}