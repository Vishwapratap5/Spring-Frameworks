package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.DAO.LoanRepository;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanListResponseDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import com.guru.springsecurity_learning.Enums.AccountStatus;
import com.guru.springsecurity_learning.Enums.LoanStatus;
import com.guru.springsecurity_learning.Exception.InvalidOperationException;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.Loan;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;




    @Override
    public LoanResponseDTO createLoan(LoanCreationRequestDTO loanCreationRequest) {

        Customer customer = customerRepository.findById(loanCreationRequest.getCustomerId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Customer not found")
                );

        Account repaymentAccount = accountRepository.findById(loanCreationRequest.getRepaymentAccountId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Repayment account not found")
                );

        if (!repaymentAccount.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
            throw new InvalidOperationException(
                    "Repayment account does not belong to the given customer"
            );
        }
        if (repaymentAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new InvalidOperationException(
                    "Repayment account must be ACTIVE"
            );
        }

        Loan loan = new Loan();
        loan.setCustomer( customer);
        loan.setRepaymentAccount(repaymentAccount);
        loan.setLoanType(loanCreationRequest.getLoanType());
        loan.setPrincipalAmount(loanCreationRequest.getPrincipalAmount());
        loan.setInterestRate(loanCreationRequest.getInterestRate());
        loan.setTenureMonths(loanCreationRequest.getTenureMonths());
        loan.setStartDate(loanCreationRequest.getStartDate());

        loan.setLoanStatus(LoanStatus.PENDING);
        loan.setOutstandingAmount(loanCreationRequest.getPrincipalAmount());

        Loan savedLoan = loanRepository.save(loan);

        return mapToResponseDTO(savedLoan);
    }



    @Override
    public LoanResponseDTO activateLoan(Long loanId) {
        Loan loan=loanRepository.findById(loanId).orElseThrow(()->new EntityNotFoundException("Loan not found"));

        if (loan.getLoanStatus() != LoanStatus.PENDING) {
            throw new InvalidOperationException(
                    "Only PENDING loans can be activated. Current status: "
                            + loan.getLoanStatus()
            );
        }

        loan.setLoanStatus(LoanStatus.ACTIVE);
        loanRepository.save(loan);
        return mapToResponseDTO(loan);
    }



    @Override
    public LoanResponseDTO cancelLoan(Long loanId) {
        Loan loan=loanRepository.findById(loanId).orElseThrow(()->new EntityNotFoundException("Loan not found"));

        if (loan.getLoanStatus() != LoanStatus.PENDING) {
            throw new InvalidOperationException(
                    "This Loan's Current status is already: "
                            + loan.getLoanStatus()
            );
        }

        loan.setLoanStatus(LoanStatus.CANCELLED);
        loanRepository.save(loan);
        return mapToResponseDTO(loan);
    }

    @Override
    public LoanResponseDTO closeLoan(Long loanId) {
        loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        // Phase-1 deliberately blocks closure
        throw new UnsupportedOperationException(
                "Loan closure is not supported in Phase 1. EMI module required."
        );

    }

    @Override
    public LoanListResponseDTO getLoansByCustomer(int page, int size, String sortBy, String direction,Long customerId) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Loan> loans=loanRepository.findByCustomerId(customerId,pageable);

       List<LoanResponseDTO> loansResponseDTO=loans.getContent().stream().map(this::mapToResponseDTO).toList();

       LoanListResponseDTO loanListResponseDTO=new LoanListResponseDTO();
       loanListResponseDTO.setEmis(loansResponseDTO);
       loanListResponseDTO.setPageNumber(page);
       loanListResponseDTO.setPageSize(size);
       loanListResponseDTO.setTotalPages(loans.getTotalPages());
       loanListResponseDTO.setTotalElements(loans.getTotalElements());
       loanListResponseDTO.setLast(loans.isLast());
       return loanListResponseDTO;
    }

    @Override
    public LoanListResponseDTO myLoans(int page, int size, String sortBy, String direction) {
        Customer curreCustomer=currentUserService.getCurrentCustomer();
        Page<Loan> loans=loanRepository.findByCustomer(curreCustomer);
        List<LoanResponseDTO>  loansResponseDTO=loans.getContent().stream().map(this::mapToResponseDTO).toList();
        LoanListResponseDTO loanListResponseDTO=new LoanListResponseDTO();
        loanListResponseDTO.setEmis(loansResponseDTO);
        loanListResponseDTO.setPageNumber(page);
        loanListResponseDTO.setPageSize(size);
        loanListResponseDTO.setTotalPages(loans.getTotalPages());
        loanListResponseDTO.setTotalElements(loans.getTotalElements());
        loanListResponseDTO.setLast(loans.isLast());
        return loanListResponseDTO;
    }


    private LoanResponseDTO mapToResponseDTO(Loan loan) {
        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setLoanId(loan.getLoanId());
        dto.setLoanType(loan.getLoanType());
        dto.setLoanStatus(loan.getLoanStatus());
        dto.setPrincipalAmount(loan.getPrincipalAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setTenureMonths(loan.getTenureMonths());
        dto.setOutstandingAmount(loan.getOutstandingAmount());
        dto.setStartDate(loan.getStartDate());
        dto.setCreatedAt(loan.getCreatedAt());
        return dto;
    }
}
