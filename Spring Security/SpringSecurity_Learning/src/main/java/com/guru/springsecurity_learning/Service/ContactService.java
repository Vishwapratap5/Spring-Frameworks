package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.ContactDTOs.AdminCreateContactDTO;
import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactListResponseDTO;
import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;

import java.util.List;

public interface ContactService {

    ContactListResponseDTO getAllContacts(int page, int size, String sortBy, String direction);

    ContactResponseDTO getContactById(Long id);

    ContactResponseDTO createContact(AdminCreateContactDTO request);

    ContactResponseDTO updateContact(Long id,AdminCreateContactDTO request);

    ContactResponseDTO deleteContactById(Long id);
}
