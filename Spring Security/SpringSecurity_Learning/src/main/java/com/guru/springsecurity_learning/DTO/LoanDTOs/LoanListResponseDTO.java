package com.guru.springsecurity_learning.DTO.LoanDTOs;

import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanListResponseDTO {

    List<LoanResponseDTO> emis;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
