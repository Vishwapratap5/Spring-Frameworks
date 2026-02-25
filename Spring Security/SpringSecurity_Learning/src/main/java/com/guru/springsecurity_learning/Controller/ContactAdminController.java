package com.guru.springsecurity_learning.Controller;


import com.guru.springsecurity_learning.DTO.ContactDTOs.AdminCreateContactDTO;
import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.Service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/contact")
@PreAuthorize("hasRole('ADMIN')")
public class ContactAdminController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<ContactResponseDTO> createContact(@Valid @RequestBody AdminCreateContactDTO request) {
        return new ResponseEntity<>(contactService.createContact(request), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ContactResponseDTO> updateContact(@Valid @PathVariable Long id,@Valid @RequestBody AdminCreateContactDTO request){
        return new ResponseEntity<>(contactService.updateContact(id,request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ContactResponseDTO> deleteContactById(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.deleteContactById(id),HttpStatus.OK);
    }
}
