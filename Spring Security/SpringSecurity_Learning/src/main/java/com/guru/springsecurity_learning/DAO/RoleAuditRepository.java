package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.RoleAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleAuditRepository extends JpaRepository<RoleAudit, Long> {
    List<RoleAudit> findByTargetCustomerId(long targetId);

    List<RoleAudit> findByPerformedByCustomerId(long actorId);

    List<RoleAudit> findByRole(Role role);

    List<RoleAudit> findByAction(AuditAction action);
}