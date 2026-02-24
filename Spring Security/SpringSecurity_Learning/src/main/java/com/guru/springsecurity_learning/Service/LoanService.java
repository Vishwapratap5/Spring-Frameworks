package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface LoanService {
    LoanResponseDTO createLoan(@Valid LoanCreationRequestDTO loanCreationRequest);

    LoanResponseDTO activateLoan(@Valid Long loanId);

    LoanResponseDTO cancelLoan(@Valid Long loanId);

    LoanResponseDTO closeLoan(@Valid Long loanId);

    List<LoanResponseDTO> getLoansByCustomer(Long customerId);

    List<LoanResponseDTO> myLoans();
}
