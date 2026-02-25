package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiListResponseDTO;
import com.guru.springsecurity_learning.Service.EmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/emis")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class EmiAdminController {

    @Autowired
    private final EmiService emiService;

    public EmiAdminController(EmiService emiService) {
        this.emiService = emiService;
    }


    @GetMapping("/loan/{loanId}")
    public ResponseEntity<EmiListResponseDTO> getEmisByLoan(
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
            @RequestParam(name="direction",defaultValue = "desc") String direction,
            @PathVariable Long loanId) {

        return ResponseEntity.ok(
                emiService.getEmisByLoan(page, size, sortBy, direction,loanId)
        );
    }

    @PostMapping("/loan/{loanId}/generate")
    public ResponseEntity<Void> generateEmiSchedule(
            @PathVariable Long loanId) {

        emiService.generateEmiSchedule(loanId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
