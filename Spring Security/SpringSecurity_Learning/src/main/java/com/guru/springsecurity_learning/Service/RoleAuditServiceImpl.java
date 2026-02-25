package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.RoleAuditRepository;
import com.guru.springsecurity_learning.DTO.RoleAudit.RoleAuditListResponse;
import com.guru.springsecurity_learning.DTO.RoleAudit.RoleAuditResponseDTO;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.RoleAudit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public RoleAuditListResponse findAll(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
       Page<RoleAudit> roleAudits = roleAuditRepository.findAll(pageable);

       List<RoleAuditResponseDTO> roleAuditDTOs=roleAudits.getContent().stream().map(roleAudit->modelMapper.map(roleAudit,RoleAuditResponseDTO.class)).toList();

       RoleAuditListResponse roleAuditListResponse=new RoleAuditListResponse();
       roleAuditListResponse.setRoleAuditDTOs(roleAuditDTOs);
       roleAuditListResponse.setTotalElements(roleAudits.getTotalElements());
       roleAuditListResponse.setTotalPages(roleAudits.getTotalPages());
       roleAuditListResponse.setPageNumber(roleAudits.getNumber());
       roleAuditListResponse.setPageSize(roleAudits.getSize());
       roleAuditListResponse.setLast(roleAudits.isLast());
       return roleAuditListResponse;


    }

    @Override
    public RoleAuditListResponse findByTarget(int page, int size, String sortBy, String direction,long targetId) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RoleAudit> roleAudits=roleAuditRepository.findByTargetCustomerId(targetId,pageable);
        return roleAudits.stream().map(roleAudit -> modelMapper.map(roleAudit, RoleAuditResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RoleAuditListResponse findByActor(int page, int size, String sortBy, String direction,long actorId) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RoleAudit> roleAudits=roleAuditRepository.findByPerformedByCustomerId(actorId,pageable);
       List<RoleAuditResponseDTO> roleAuditsResponse=roleAudits.getContent().stream().map(roleAudit->modelMapper.map(roleAudit,RoleAuditResponseDTO.class)).toList();
       RoleAuditListResponse roleAuditListResponse=new RoleAuditListResponse();
       roleAuditListResponse.setRoleAuditDTOs(roleAuditsResponse);
       roleAuditListResponse.setTotalElements(roleAudits.getTotalElements());
       roleAuditListResponse.setTotalPages(roleAudits.getTotalPages());
       roleAuditListResponse.setPageNumber(roleAudits.getNumber());
       roleAuditListResponse.setPageSize(roleAudits.getSize());
       roleAuditListResponse.setLast(roleAudits.isLast());
       return roleAuditListResponse;
    }

    @Override
    public RoleAuditListResponse findByRole(int page, int size, String sortBy, String direction,Role role) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RoleAudit> roleAudits=roleAuditRepository.findByRole(role,pageable);
       List<RoleAuditResponseDTO> roleAuditDTOs=roleAudits.getContent().stream().map(roleAudit->modelMapper.map(roleAudit,RoleAuditResponseDTO.class)).toList();
       RoleAuditListResponse roleAuditListResponse=new RoleAuditListResponse();
       roleAuditListResponse.setRoleAuditDTOs(roleAuditDTOs);
       roleAuditListResponse.setTotalElements(roleAudits.getTotalElements());
       roleAuditListResponse.setTotalPages(roleAudits.getTotalPages());
       roleAuditListResponse.setPageNumber(roleAudits.getNumber());
       roleAuditListResponse.setPageSize(roleAudits.getSize());
       roleAuditListResponse.setLast(roleAudits.isLast());
       return roleAuditListResponse;
    }

    @Override
    public RoleAuditListResponse findByAction(int page, int size, String sortBy, String direction,AuditAction action) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RoleAudit> roleAudits=roleAuditRepository.findByAction(action,pageable);
       List<RoleAuditResponseDTO> roleAuditDTOs=roleAudits.getContent().stream().map(a->modelMapper.map(a,RoleAuditResponseDTO.class)).toList();
       RoleAuditListResponse roleAuditListResponse=new RoleAuditListResponse();
       roleAuditListResponse.setRoleAuditDTOs(roleAuditDTOs);
       roleAuditListResponse.setTotalElements(roleAudits.getTotalElements());
       roleAuditListResponse.setTotalPages(roleAudits.getTotalPages());
       roleAuditListResponse.setPageNumber(roleAudits.getNumber());
       roleAuditListResponse.setPageSize(roleAudits.getSize());
       roleAuditListResponse.setLast(roleAudits.isLast());
       return roleAuditListResponse;
    }

}
