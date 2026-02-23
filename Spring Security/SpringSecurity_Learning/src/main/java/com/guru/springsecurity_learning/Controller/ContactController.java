package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.ContactDTOs.AdminCreateContactDTO;
import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.Service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/contact")
@PreAuthorize("hasRole('USER')")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {
        List<ContactResponseDTO> contacts=contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.getContactById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> deleteContactById(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.deleteContactById(id),HttpStatus.OK);
    }

}
