package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Enums.CardType;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Card;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByCardTypeAndAccount(@NotNull CardType cardType, Account account);

    Page<Card> findByAccount_Customer(Customer currentCustomer, Pageable pageable);

    Optional<Card> findByIdAndAccount_Customer(Long cardId, Customer currentCustomer);
}