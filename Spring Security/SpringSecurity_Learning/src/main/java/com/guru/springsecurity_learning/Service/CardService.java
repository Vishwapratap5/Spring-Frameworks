package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.CardDTO.CardCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.CardDTO.CardListResponseDTO;
import com.guru.springsecurity_learning.DTO.CardDTO.CardResponseDTO;
import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Model.Card;

import java.util.List;

public interface CardService {
    CardResponseDTO create(CardCreationRequestDTO card);

    void blockCard(Long cardId);

    void unblockCard(Long cardId);

    void closeCard(Long cardId);

    CardListResponseDTO getCards(int page, int size, String sortBy, String direction);

    CardResponseDTO getMyCard(Long cardId);

}
