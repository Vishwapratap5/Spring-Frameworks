package com.guru.springsecurity_learning.DTO.CardDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CardResponseDTO {
    private Long cardId;

    private String cardType;

    private String maskedCardNumber; // never raw number

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;

    private Date createDt;
}
