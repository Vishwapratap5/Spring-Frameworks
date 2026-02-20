package com.guru.springsecurity_learning.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guru.springsecurity_learning.Enums.AuthProvider;
import com.guru.springsecurity_learning.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false,unique = true)
    @NotBlank
    private String customerName;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @Size(min = 10,max = 10)
    @NotBlank
    private String mobileNumber;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(unique = true)
    private String providerUserId;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Card> cards;


    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany
    @JsonIgnore
    private List<Loan> loans;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Authority>  authorities;


}
