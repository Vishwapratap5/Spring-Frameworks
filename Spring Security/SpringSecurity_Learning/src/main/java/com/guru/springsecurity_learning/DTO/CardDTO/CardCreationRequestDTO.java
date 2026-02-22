package com.guru.springsecurity_learning.DTO.CardDTO;


import com.guru.springsecurity_learning.Enums.CardType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardCreationRequestDTO {

    @NotNull
    private Long accountId;

    @NotNull
    private CardType cardType; // DEBIT / CREDIT

    // Only required for CREDIT cards
    private BigDecimal cardLimit;

}
