package com.guru.springsecurity_learning.Model;

import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "role_audits")
public class RoleAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long performedByCustomerId;

    private Long targetCustomerId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuditAction action; // ASSIGN / REMOVE

    private LocalDateTime timestamp;

}
