package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.Enums.CardType;

public interface CardNumberGenerationService {

    String generateCardNumber(CardType cardType);
}
