package com.guru.springsecurity_learning.Service;


import com.guru.springsecurity_learning.DTO.RoleAuditResponseDTO;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;

import java.util.List;

public interface RoleAuditService {
    List<RoleAuditResponseDTO> findAll();

    List<RoleAuditResponseDTO> findByTarget(long targetId);

    List<RoleAuditResponseDTO> findByActor(long actorId);

    List<RoleAuditResponseDTO> findByRole(Role role);

    List<RoleAuditResponseDTO> findByAction(AuditAction action);
}
