package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.RoleAuditRepository;
import com.guru.springsecurity_learning.DTO.RoleAuditResponseDTO;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.RoleAudit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuditServiceImpl implements RoleAuditService {
    @Autowired
    private RoleAuditRepository roleAuditRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleAuditResponseDTO> findAll() {
       List<RoleAudit> roleAudits = roleAuditRepository.findAll();
       return roleAudits.stream().map(roleAudit -> modelMapper.map(roleAudit, RoleAuditResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoleAuditResponseDTO> findByTarget(long targetId) {
        List<RoleAudit> roleAudits=roleAuditRepository.findByTargetCustomerId(targetId);
        return roleAudits.stream().map(roleAudit -> modelMapper.map(roleAudit, RoleAuditResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoleAuditResponseDTO> findByActor(long actorId) {
        List<RoleAudit> roleAudits=roleAuditRepository.findByPerformedByCustomerId(actorId);
        return roleAudits.stream().map(roleAudit -> modelMapper.map(roleAudit, RoleAuditResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoleAuditResponseDTO> findByRole(Role role) {
        List<RoleAudit> roleAudits=roleAuditRepository.findByRole(role);
        return roleAudits.stream().map(roleAudit -> modelMapper.map(roleAudit, RoleAuditResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoleAuditResponseDTO> findByAction(AuditAction action) {
        List<RoleAudit> roleAudits=roleAuditRepository.findByAction(action);
        return roleAudits.stream().map(roleAudit -> modelMapper.map(roleAudit, RoleAuditResponseDTO.class)).collect(Collectors.toList());
    }

}
