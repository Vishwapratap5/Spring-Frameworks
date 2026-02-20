package com.guru.springsecurity_learning.Service;


import com.guru.springsecurity_learning.DAO.ContactRepository;
import com.guru.springsecurity_learning.DTO.ContactDTOs.AdminCreateContactDTO;
import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.Exception.ResourceNotFoundException;
import com.guru.springsecurity_learning.Model.Contact;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContactResponseDTO> getAllContacts() {
        List<Contact> contacts=contactRepository.findAll();

       if(contacts.isEmpty()){
           throw new ResourceNotFoundException("Contacts not found");
       }
       return contacts.stream().map(contact->modelMapper.map(contact,ContactResponseDTO.class))
                                                              .collect(Collectors.toList());
    }

    @Override
    public ContactResponseDTO getContactById(Long id) {
        Contact contact=contactRepository.findByContactId(id).orElseThrow(()->new ResourceNotFoundException("Contact not found"));
        return modelMapper.map(contact,ContactResponseDTO.class);
    }

    @Override
    public ContactResponseDTO createContact(AdminCreateContactDTO request) {
        Contact contact=modelMapper.map(request,Contact.class);
        contactRepository.save(contact);
        return modelMapper.map(contact,ContactResponseDTO.class);
    }

    @Override
    public ContactResponseDTO updateContact(Long id,AdminCreateContactDTO request) {
        Contact contact=contactRepository.findByContactId(id).orElseThrow(()->new ResourceNotFoundException("Contact not found"));
        contact.setContactEmail(request.getContactEmail());
        contact.setContactName(request.getContactName());
        contact.setSubject(request.getSubject());
        contact.setMessage(request.getMessage());
        contactRepository.save(contact);
        return modelMapper.map(contact,ContactResponseDTO.class);
    }

    @Override
    public ContactResponseDTO deleteContactById(Long id) {
        Contact contact=contactRepository.findByContactId(id).orElseThrow(()->new ResourceNotFoundException("Contact not found"));
        contactRepository.delete(contact);
        return modelMapper.map(contact,ContactResponseDTO.class);
    }
}
