package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiListResponseDTO;
import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiResponseDTO;
import com.guru.springsecurity_learning.Service.EmiService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/emis")
@PreAuthorize("hasRole('USER')")
public class EmiController {

    private final EmiService emiService;

    public EmiController(EmiService emiService) {
        this.emiService = emiService;
    }

    // View EMIs of user's own loan
    @GetMapping("/loan/{loanId}")
    public ResponseEntity<EmiListResponseDTO> getMyEmis(@RequestParam(name="page",defaultValue = "0") int page,
                                                        @RequestParam(name="size",defaultValue = "10") int size,
                                                        @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                        @RequestParam(name="direction",defaultValue = "desc") String direction,
                                                        @PathVariable Long loanId) {

        return ResponseEntity.ok(
                emiService.getEmisByLoan(page, size, sortBy, direction,loanId)
        );
    }

    // Pay a single EMI
    @PostMapping("/{emiId}/pay")
    public ResponseEntity<EmiResponseDTO> payEmi(
            @PathVariable Long emiId) {

        return ResponseEntity.ok(
                emiService.payEmi(emiId)
        );
    }
}
