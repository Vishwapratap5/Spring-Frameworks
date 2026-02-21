package com.guru.springsecurity_learning.Controller;


import com.guru.springsecurity_learning.DTO.CustomerToAdminRequestDTO;
import com.guru.springsecurity_learning.Service.SuperAdminPanelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-admin/panel")
@PreAuthorize("hasRole('SUPER_ADMIN')")
@AllArgsConstructor
public class SuperAdminPanelController {

    @Autowired
    private SuperAdminPanelService adminPanelService;

    @PutMapping("/assign-roles")
    public ResponseEntity<String> assignRole(@Valid @RequestBody CustomerToAdminRequestDTO customer){
        adminPanelService.assignRole(customer.getCustomerId(),customer.getRole());
        return new ResponseEntity<>( "Role Assigned Successfully..! Role changes take effect after token expiry or re-login...",HttpStatus.OK);
    }

    @DeleteMapping("/delete-role")
    public ResponseEntity<String> deleteRole(@RequestBody CustomerToAdminRequestDTO customer){
        adminPanelService.deleteRole(customer.getCustomerId(),customer.getRole());
        return new ResponseEntity<>( "Role removed Successfully..! Role changes take effect after token expiry or re-login.”",HttpStatus.OK);
    }
}
