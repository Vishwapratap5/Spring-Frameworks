package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiListResponseDTO;
import com.guru.springsecurity_learning.DTO.EmiDTOs.EmiResponseDTO;

import java.util.List;

public interface EmiService {

    void generateEmiSchedule(Long loanId);


    EmiListResponseDTO getEmisByLoan(int page, int size, String sortBy, String direction,Long loanId);

    EmiResponseDTO payEmi(Long emiId);
}
