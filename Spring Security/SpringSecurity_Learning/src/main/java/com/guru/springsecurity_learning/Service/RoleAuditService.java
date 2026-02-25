package com.guru.springsecurity_learning.Service;


import com.guru.springsecurity_learning.DTO.RoleAudit.RoleAuditListResponse;
import com.guru.springsecurity_learning.DTO.RoleAudit.RoleAuditResponseDTO;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;

import java.util.List;

public interface RoleAuditService {
    RoleAuditListResponse findAll(int page, int size, String sortBy, String direction);

    RoleAuditListResponse findByTarget(int page, int size, String sortBy, String direction,long targetId);

    RoleAuditListResponse findByActor(int page, int size, String sortBy, String direction,long actorId);

    RoleAuditListResponse findByRole(int page, int size, String sortBy, String direction,Role role);

    RoleAuditListResponse findByAction(int page, int size, String sortBy, String direction,AuditAction action);
}
