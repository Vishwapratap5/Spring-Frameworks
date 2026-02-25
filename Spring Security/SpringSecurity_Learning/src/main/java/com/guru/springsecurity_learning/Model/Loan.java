package com.guru.springsecurity_learning.Model;

import com.guru.springsecurity_learning.Enums.LoanStatus;
import com.guru.springsecurity_learning.Enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    //Who owns the loan
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // From which account EMI will be debited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repayment_account_id", nullable = false)
    private Account repaymentAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanType loanType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus loanStatus;


    @Column(nullable = false)
    private BigDecimal principalAmount;

    @Column(nullable = false)
    private BigDecimal interestRate; // yearly %

    @Column(nullable = false)
    private Integer tenureMonths;


    @Column(nullable = false)
    private BigDecimal outstandingAmount;

    @Column(nullable = false)
    private LocalDate startDate;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}