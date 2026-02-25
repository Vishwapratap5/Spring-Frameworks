package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanListResponseDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface LoanService {
    LoanResponseDTO createLoan(@Valid LoanCreationRequestDTO loanCreationRequest);

    LoanResponseDTO activateLoan(@Valid Long loanId);

    LoanResponseDTO cancelLoan(@Valid Long loanId);

    LoanResponseDTO closeLoan(@Valid Long loanId);

    LoanListResponseDTO getLoansByCustomer(int page, int size, String sortBy, String direction, Long customerId);

    LoanListResponseDTO myLoans(int page, int size, String sortBy, String direction);
}
