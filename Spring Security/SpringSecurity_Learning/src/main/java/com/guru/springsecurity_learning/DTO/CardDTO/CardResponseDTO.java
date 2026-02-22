
package com.guru.springsecurity_learning.DTO.CardDTO;

import com.guru.springsecurity_learning.Enums.CardStatus;
import com.guru.springsecurity_learning.Enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO {

    private Long cardId;

    private String maskedCardNumber; // XXXX-XXXX-XXXX-1234

    private CardType cardType;

    private CardStatus status;

    private BigDecimal cardLimit;

    private LocalDate expiryDate;

    private LocalDateTime createdAt;

    private Long accountId;
}
