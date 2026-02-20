package com.guru.springsecurity_learning.Model;

import com.guru.springsecurity_learning.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
