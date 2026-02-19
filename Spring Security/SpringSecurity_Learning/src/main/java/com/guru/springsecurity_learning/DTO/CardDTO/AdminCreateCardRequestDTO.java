package com.guru.springsecurity_learning.DTO.CardDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  AdminCreateCardRequestDTO{

    @NotNull
    private Long customerId;

    @NotBlank
    private String cardType;           // CREDIT / DEBIT

    @NotNull
    @Positive
    private Integer totalLimit;        // approved limit


}
