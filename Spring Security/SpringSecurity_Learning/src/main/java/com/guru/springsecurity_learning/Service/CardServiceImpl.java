package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DAO.CardRepository;
import com.guru.springsecurity_learning.DTO.CardDTO.CardCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.CardDTO.CardResponseDTO;
import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Enums.AccountStatus;
import com.guru.springsecurity_learning.Enums.CardStatus;
import com.guru.springsecurity_learning.Enums.CardType;
import com.guru.springsecurity_learning.Exception.InvalidAmountException;
import com.guru.springsecurity_learning.Exception.InvalidOperationException;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Card;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private CardNumberGenerationService cardNumberGenerationService;
    @Autowired
    private AccountService accountService;


    @Override
    @Transactional
    public CardResponseDTO create(CardCreationRequestDTO card) {

        Long accountId = card.getAccountId();
        Customer currentCustomer=currentUserService.getCurrentCustomer();
        Account account=accountRepository.findByIdAndCustomer(accountId,currentCustomer).orElseThrow(()->new EntityNotFoundException("Account not found"));

        if(account.getStatus()!= AccountStatus.ACTIVE){
            throw new InvalidOperationException("Can't issue "+card.getCardType()+" due to AccountStatus: "+account.getStatus());
        }

        if(card.getCardType()== CardType.DEBIT){
            boolean isCardExist=cardRepository.existsByCardTypeAndAccount(card.getCardType(),account);

            if(isCardExist){
                throw new EntityExistsException("Card already exists can't issue new card..");
            }

        }

        Card newCard = new Card();
        newCard.setAccount(account);
        newCard.setCardType(card.getCardType());
        newCard.setCardStatus(CardStatus.ACTIVE);

        // Generate card number (BIN + random + Luhn check digit)
        newCard.setCardNumber(
                cardNumberGenerationService.generateCardNumber(card.getCardType())
        );

        // Expiry: 5 years from now (industry-like default)
        newCard.setExpiryDate(LocalDate.now().plusYears(5));

        // Credit limit only for CREDIT cards
        if (card.getCardType() == CardType.CREDIT) {
            if (card.getCardLimit() == null ||
                    card.getCardLimit().compareTo(BigDecimal.ZERO) <= 0) {

                throw new InvalidAmountException(
                        "Credit limit is required for CREDIT card"
                );
            }
            newCard.setCardLimit(card.getCardLimit());
        }

        Card saved = cardRepository.save(newCard);
        return mapToResponse(saved);
    }



    @Override
    @Transactional
    public void blockCard(Long cardId) {
        Card card= getCardById(cardId);
        if(card.getCardStatus()== CardStatus.BLOCKED){
            throw new InvalidOperationException("Card already blocked");
        }
        if(card.getCardStatus()== CardStatus.CLOSED){
            throw new InvalidOperationException("Card already Closed");
        }
        card.setCardStatus(CardStatus.BLOCKED);
        cardRepository.save(card);
    }



    @Override
    @Transactional
    public void unblockCard(Long cardId) {
        Card card= getCardById(cardId);
        if(card.getCardStatus()!= CardStatus.BLOCKED){
            throw new InvalidOperationException("Card already Active");
        }
        card.setCardStatus(CardStatus.ACTIVE);
        cardRepository.save(card);
    }



    @Override
    @Transactional
    public void closeCard(Long cardId) {
        Card card= getCardById(cardId);
        if(card.getCardStatus()== CardStatus.CLOSED){
            throw new InvalidOperationException("Card already Closed");
        }
        card.setCardStatus(CardStatus.CLOSED);
        cardRepository.save(card);
    }



    @Override
    public List<CardResponseDTO> getCards() {

        Customer currentCustomer = currentUserService.getCurrentCustomer();

        List<Card> cards =
                cardRepository.findByAccount_Customer(currentCustomer);

        return cards.stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public CardResponseDTO getMyCard(Long cardId) {
        Customer currentCustomer = currentUserService.getCurrentCustomer();

        Card card = cardRepository.findByIdAndAccount_Customer(cardId, currentCustomer).orElseThrow(() ->
                        new EntityNotFoundException("Card not found"));

        return mapToResponse(card);
    }



    private Card getCardById(Long cardId){
        return cardRepository.findById(cardId).orElseThrow(()->new EntityNotFoundException("Card not found"));
    }

    private CardResponseDTO mapToResponse(Card card) {
        CardResponseDTO dto = new CardResponseDTO();
        dto.setCardId(card.getCardId());
        dto.setAccountId(card.getAccount().getId());
        dto.setCardType(card.getCardType());
        dto.setStatus(card.getCardStatus());
        dto.setCardLimit(card.getCardLimit());
        dto.setExpiryDate(card.getExpiryDate());
        dto.setCreatedAt(card.getCreatedAt());

        String cardNo = card.getCardNumber();
        dto.setMaskedCardNumber(
                "XXXX-XXXX-XXXX-" + cardNo.substring(cardNo.length() - 4)
        );

        return dto;
    }
}
