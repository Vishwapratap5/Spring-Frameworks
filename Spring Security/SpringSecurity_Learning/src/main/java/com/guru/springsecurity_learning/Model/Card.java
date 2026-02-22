package com.guru.springsecurity_learning.Model;


import com.guru.springsecurity_learning.Enums.CardStatus;
import com.guru.springsecurity_learning.Enums.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    @Column(name = "card_number",unique = true,nullable = false)
    private String cardNumber;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "card_limit")
    private BigDecimal cardLimit;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @Column(name = "create_dt",nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDate expiryDate;

}