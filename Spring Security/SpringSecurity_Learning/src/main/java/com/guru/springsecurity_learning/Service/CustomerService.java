package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerLoginRequestDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerRegisterRequestDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerResponseDTO;
import com.guru.springsecurity_learning.Enums.AuthProvider;
import jakarta.validation.Valid;

import java.util.Optional;

public interface CustomerService {

    CustomerResponseDTO localCustomerRegistration(CustomerRegisterRequestDTO customer);


    public CustomerResponseDTO loginUser(@Valid CustomerLoginRequestDTO request);

    CustomerResponseDTO oAuthRegistration(String email, String name,
                                          String providerUserId,
                                          AuthProvider provider);

    CustomerResponseDTO updateMobileNumber(Long customerId, String mobileNumber);

    Optional<CustomerResponseDTO> findByEmail(String email);

    CustomerResponseDTO getCustomerById(Long customerId);
}
