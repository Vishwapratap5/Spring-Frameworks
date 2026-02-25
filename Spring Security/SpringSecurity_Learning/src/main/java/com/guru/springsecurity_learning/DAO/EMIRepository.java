package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Enums.EmiStatus;
import com.guru.springsecurity_learning.Model.EMI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EMIRepository extends JpaRepository<EMI, Long> {
    boolean existsByLoanLoanId(Long loanId);

    Page<EMI> findByLoanLoanIdOrderByInstallmentNumber(Long loanId, Pageable pageable);

    boolean existsByLoanLoanIdAndStatus(Long loanId, EmiStatus emiStatus);
}