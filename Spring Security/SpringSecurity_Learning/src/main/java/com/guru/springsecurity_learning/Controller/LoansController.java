package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import com.guru.springsecurity_learning.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/loans")
@PreAuthorize("hasRole('USER')")
public class LoansController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/my")
    public ResponseEntity<List<LoanResponseDTO>> myLoans() {
        return ResponseEntity.ok(
                loanService.myLoans()
        );
    }
}
