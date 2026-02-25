package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanListResponseDTO;
import com.guru.springsecurity_learning.DTO.LoanDTOs.LoanResponseDTO;
import com.guru.springsecurity_learning.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/loans")
@PreAuthorize("hasRole('USER')")
public class LoansController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/my")
    public ResponseEntity<LoanListResponseDTO> myLoans(  @RequestParam(name="page",defaultValue = "0") int page,
                                                         @RequestParam(name="size",defaultValue = "10") int size,
                                                         @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                         @RequestParam(name="direction",defaultValue = "desc") String direction) {
        return ResponseEntity.ok(
                loanService.myLoans(page, size, sortBy, direction)
        );
    }
}
