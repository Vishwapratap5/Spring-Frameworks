package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanListResponseDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import com.guru.springsecurity_learning.Service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/loans")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class LoansAdminController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<LoanResponseDTO> create(@Valid @RequestBody LoanCreationRequestDTO loanCreationRequest){
        return new ResponseEntity<>(loanService.createLoan(loanCreationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/{loanId}/activate")
    public ResponseEntity<LoanResponseDTO> activate(@Valid Long loanId){
        return new ResponseEntity<>(loanService.activateLoan(loanId), HttpStatus.CREATED);
    }

    @PostMapping("/{loanId}/cancel")
    public ResponseEntity<LoanResponseDTO> cancel(@Valid Long loanId){
        return new ResponseEntity<>(loanService.cancelLoan(loanId), HttpStatus.CREATED);
    }

    @PostMapping("/{loanId}/close")
    public ResponseEntity<LoanResponseDTO> close(@Valid Long loanId){
        return new ResponseEntity<>(loanService.closeLoan(loanId), HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<LoanListResponseDTO> getLoansByCustomer(
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
            @RequestParam(name="direction",defaultValue = "desc") String direction,
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                loanService.getLoansByCustomer(page, size, sortBy, direction,customerId)
        );
    }
}
