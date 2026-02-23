package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.CardRepository;
import com.guru.springsecurity_learning.DAO.TransactionRepository;
import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Enums.CardStatus;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Card;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CardTransactionServiceImpl implements CardTransactionService {

    private final CardRepository cardRepository;
    private final TransactionService transactionService;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional
    public TransactionResponseDTO payByCard(Long cardId, BigDecimal amount,String transactionRef) {


        Customer currentCustomer = currentUserService.getCurrentCustomer();

        Card card = cardRepository
                .findByIdAndAccount_Customer(cardId, currentCustomer)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

        // 🔐 CARD CHECKS (THIS WAS MISSING EARLIER)
        if (card.getCardStatus() != CardStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Card is not active: " + card.getCardStatus()
            );
        }

        if (LocalDate.now().isAfter(card.getExpiryDate())) {
            throw new IllegalStateException("Card has expired");
        }

        Account account = card.getAccount();

        // 💰 Delegate money movement
        return transactionService.debit(account.getId(), amount,transactionRef);
    }
}
