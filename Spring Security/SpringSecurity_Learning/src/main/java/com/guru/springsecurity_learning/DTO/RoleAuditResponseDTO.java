package com.guru.springsecurity_learning.DTO;

import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RoleAuditResponseDTO {

    private Long auditId;
    private Long performedByCustomerId;
    private Long targetCustomerId;
    private Role role;
    private AuditAction action;
    private LocalDateTime timestamp;
}
