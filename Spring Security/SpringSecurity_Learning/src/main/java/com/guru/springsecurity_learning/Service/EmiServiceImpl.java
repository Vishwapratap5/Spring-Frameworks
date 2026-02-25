package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.EMIRepository;
import com.guru.springsecurity_learning.DAO.LoanRepository;
import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiListResponseDTO;
import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiResponseDTO;
import com.guru.springsecurity_learning.Enums.EmiStatus;
import com.guru.springsecurity_learning.Enums.LoanStatus;
import com.guru.springsecurity_learning.Exception.InvalidOperationException;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.EMI;
import com.guru.springsecurity_learning.Model.Loan;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmiServiceImpl implements EmiService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private EMIRepository emiRepository;

    @Autowired
    private CurrentUserService currentUserService;



    @Override
    @Transactional
    public void generateEmiSchedule(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        if (loan.getLoanStatus() != LoanStatus.ACTIVE) {
            throw new InvalidOperationException(
                    "EMI schedule can be generated only for ACTIVE loans"
            );
        }

        if (emiRepository.existsByLoanLoanId(loanId)) {
            throw new InvalidOperationException(
                    "EMI schedule already exists for this loan"
            );
        }

        BigDecimal principal = loan.getPrincipalAmount();
        BigDecimal interestRate = loan.getInterestRate(); // annual %
        int tenureMonths = loan.getTenureMonths();

        BigDecimal totalInterest = principal
                .multiply(interestRate)
                .multiply(BigDecimal.valueOf(tenureMonths))
                .divide(BigDecimal.valueOf(12 * 100), 2, RoundingMode.HALF_UP);

        BigDecimal totalPayable = principal.add(totalInterest);

        BigDecimal emiAmount = totalPayable.divide(
                BigDecimal.valueOf(tenureMonths),
                2,
                RoundingMode.HALF_UP
        );

        LocalDate firstDueDate = loan.getStartDate().plusMonths(1);

        List<EMI> emis = new ArrayList<>();

        for (int i = 1; i <= tenureMonths; i++) {

            EMI emi = new EMI();
            emi.setLoan(loan);
            emi.setInstallmentNumber(i);
            emi.setEmiAmount(emiAmount);
            emi.setDueDate(firstDueDate.plusMonths(i - 1));
            emi.setStatus(EmiStatus.DUE);

            emis.add(emi);
        }
        emiRepository.saveAll(emis);
    }

    @Override
    public EmiListResponseDTO getEmisByLoan(int page, int size, String sortBy, String direction,Long loanId) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

            Customer currentCustomer = currentUserService.getCurrentCustomer();

            if (!loan.getCustomer().getCustomerId()
                    .equals(currentCustomer.getCustomerId())) {
                throw new InvalidOperationException(
                        "You are not allowed to view EMIs of this loan"
                );
            }

        Page<EMI> emis=emiRepository.findByLoanLoanIdOrderByInstallmentNumber(loanId,pageable);

            List<EmiResponseDTO> emiResponseDTOs=emis.getContent().stream().map(this::mapToEmiResponseDTO).toList();

            EmiListResponseDTO emiListResponseDTO=new EmiListResponseDTO();
            emiListResponseDTO.setEmis(emiResponseDTOs);
            emiListResponseDTO.setPageNumber(page);
            emiListResponseDTO.setPageSize(size);
            emiListResponseDTO.setTotalPages(emis.getTotalPages());
            emiListResponseDTO.setTotalElements(emis.getTotalElements());
            emiListResponseDTO.setLast(emis.isLast());
            return emiListResponseDTO;

    }



    @Override
    @Transactional
    public EmiResponseDTO payEmi(Long emiId) {


        EMI emi = emiRepository.findById(emiId)
                .orElseThrow(() -> new EntityNotFoundException("EMI not found"));

        Loan loan = emi.getLoan();

        if (loan.getLoanStatus() != LoanStatus.ACTIVE) {
            throw new InvalidOperationException(
                    "Cannot pay EMI for a non-ACTIVE loan"
            );
        }

        if (emi.getStatus() != EmiStatus.DUE) {
            throw new InvalidOperationException(
                    "EMI is already " + emi.getStatus()
            );
        }

            Customer currentCustomer = currentUserService.getCurrentCustomer();

            if (!loan.getCustomer().getCustomerId()
                    .equals(currentCustomer.getCustomerId())) {
                throw new InvalidOperationException(
                        "You are not allowed to pay this EMI"
                );
            }

        emi.setStatus(EmiStatus.PAID);
        emi.setPaymentDate(LocalDate.now());
        emiRepository.save(emi);

        BigDecimal newOutstanding = loan.getOutstandingAmount()
                .subtract(emi.getEmiAmount());

        if (newOutstanding.compareTo(BigDecimal.ZERO) < 0) {
            newOutstanding = BigDecimal.ZERO; // safety guard
        }

        loan.setOutstandingAmount(newOutstanding);


        boolean hasPendingEmis =
                emiRepository.existsByLoanLoanIdAndStatus(
                        loan.getLoanId(),
                        EmiStatus.DUE
                );

        if (!hasPendingEmis) {
            loan.setLoanStatus(LoanStatus.CLOSED);
        }

        loanRepository.save(loan);

        return mapToEmiResponseDTO(emi);
    }

    private EmiResponseDTO mapToEmiResponseDTO(EMI emi) {
        EmiResponseDTO dto = new EmiResponseDTO();
        dto.setEmiId(emi.getEmiId());
        dto.setInstallmentNumber(emi.getInstallmentNumber());
        dto.setEmiAmount(emi.getEmiAmount());
        dto.setDueDate(emi.getDueDate());
        dto.setStatus(emi.getStatus());
        dto.setPaymentDate(emi.getPaymentDate());
        return dto;
    }
}
