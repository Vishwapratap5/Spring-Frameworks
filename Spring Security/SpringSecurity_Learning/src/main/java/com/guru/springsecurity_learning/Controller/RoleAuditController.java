package com.guru.springsecurity_learning.Controller;


import com.guru.springsecurity_learning.DTO.RoleAuditResponseDTO;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Service.RoleAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/super-admin/panel/audit-details")
@PreAuthorize("hasAnyRole('SUPER_ADMIN','AUDITOR')")
public class RoleAuditController {
    @Autowired
    private RoleAuditService roleAuditService;

    @GetMapping("/all")
    public ResponseEntity<List<RoleAuditResponseDTO>> findAll(){
            return new ResponseEntity<>(roleAuditService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/target/{targetId}")
    public ResponseEntity<List<RoleAuditResponseDTO>> findByTarget(@PathVariable("targetId") long targetId){
        return new ResponseEntity<>(roleAuditService.findByTarget(targetId), HttpStatus.OK);
    }

    @GetMapping("/actor/{actorId}")
    public ResponseEntity<List<RoleAuditResponseDTO>> findByActor(@PathVariable("actorId") long actorId){
        return new ResponseEntity<>(roleAuditService.findByActor(actorId), HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<RoleAuditResponseDTO>> findByRole(@PathVariable("role") Role role){
        return new ResponseEntity<>(roleAuditService.findByRole(role), HttpStatus.OK);
    }

    @GetMapping("/action/{action}")
    public ResponseEntity<List<RoleAuditResponseDTO>> findByRole(@PathVariable("action") AuditAction action){
        return new ResponseEntity<>(roleAuditService.findByAction(action), HttpStatus.OK);
    }
}
