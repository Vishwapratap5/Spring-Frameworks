package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.RoleAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAuditRepository extends JpaRepository<RoleAudit, Long> {
    Page<RoleAudit> findByTargetCustomerId(long targetId, Pageable pageable);

    Page<RoleAudit> findByPerformedByCustomerId(long actorId, Pageable pageable);

    Page<RoleAudit> findByRole(Role role, Pageable pageable);

    Page<RoleAudit> findByAction(AuditAction action, Pageable pageable);
}