package com.guru.springsecurity_learning.Controller;


import com.guru.springsecurity_learning.DTO.RoleAudit.RoleAuditListResponse;
import com.guru.springsecurity_learning.DTO.RoleAudit.RoleAuditResponseDTO;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Service.RoleAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/super-admin/panel/audit-details")
@PreAuthorize("hasAnyRole('SUPER_ADMIN','AUDITOR')")
public class RoleAuditController {
    @Autowired
    private RoleAuditService roleAuditService;

    @GetMapping("/all")
    public ResponseEntity<RoleAuditListResponse> findAll(  @RequestParam(name="page",defaultValue = "0") int page,
                                                           @RequestParam(name="size",defaultValue = "10") int size,
                                                           @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                           @RequestParam(name="direction",defaultValue = "desc") String direction){
            return new ResponseEntity<>(roleAuditService.findAll(page, size, sortBy, direction), HttpStatus.OK);
    }

    @GetMapping("/target/{targetId}")
    public ResponseEntity<RoleAuditListResponse> findByTarget(  @RequestParam(name="page",defaultValue = "0") int page,
                                                                @RequestParam(name="size",defaultValue = "10") int size,
                                                                @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                                @RequestParam(name="direction",defaultValue = "desc") String direction,
                                                                @PathVariable("targetId") long targetId){
        return new ResponseEntity<>(roleAuditService.findByTarget(page, size, sortBy, direction,targetId), HttpStatus.OK);
    }

    @GetMapping("/actor/{actorId}")
    public ResponseEntity<RoleAuditListResponse> findByActor(@RequestParam(name="page",defaultValue = "0") int page,
                                                             @RequestParam(name="size",defaultValue = "10") int size,
                                                             @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                             @RequestParam(name="direction",defaultValue = "desc") String direction,
                                                             @PathVariable("actorId") long actorId){
        return new ResponseEntity<>(roleAuditService.findByActor(page, size, sortBy, direction,actorId), HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<RoleAuditListResponse> findByRole(@RequestParam(name="page",defaultValue = "0") int page,
                                                            @RequestParam(name="size",defaultValue = "10") int size,
                                                            @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                            @RequestParam(name="direction",defaultValue = "desc") String direction,
                                                            @PathVariable("role") Role role){
        return new ResponseEntity<>(roleAuditService.findByRole(page, size, sortBy, direction,role), HttpStatus.OK);
    }

    @GetMapping("/action/{action}")
    public ResponseEntity<RoleAuditListResponse> findByRole(@RequestParam(name="page",defaultValue = "0") int page,
                                                            @RequestParam(name="size",defaultValue = "10") int size,
                                                            @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                            @RequestParam(name="direction",defaultValue = "desc") String direction,
                                                            @PathVariable("action") AuditAction action){
        return new ResponseEntity<>(roleAuditService.findByAction(page, size, sortBy, direction,action), HttpStatus.OK);
    }
}
