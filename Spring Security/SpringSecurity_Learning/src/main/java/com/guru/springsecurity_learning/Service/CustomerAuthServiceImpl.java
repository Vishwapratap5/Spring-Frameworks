package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerLoginRequestDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerRegisterRequestDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerResponseDTO;
import com.guru.springsecurity_learning.Enums.AuthProvider;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Exception.ResourceAlreadyExistException;
import com.guru.springsecurity_learning.Model.Authority;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CustomerAuthServiceImpl implements CustomerAuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public CustomerResponseDTO localCustomerRegistration(CustomerRegisterRequestDTO customer) {

        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("Customer already exists");
        }

        Customer newCustomer= modelMapper.map(customer, Customer.class);
        newCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        newCustomer.setAuthProvider(AuthProvider.LOCAL);
        newCustomer.setProviderUserId(null);

        Authority authority=new Authority();
        authority.setCustomer(newCustomer);
        authority.setRole(Role.ROLE_USER);

        newCustomer.setAuthorities(new ArrayList<>());
        newCustomer.getAuthorities().add(authority);

        Customer savedCustomer=customerRepository.save(newCustomer);

        return modelMapper.map(savedCustomer,CustomerResponseDTO.class);

    }

    @Override
    public CustomerResponseDTO loginUser(CustomerLoginRequestDTO request) {
        return null;
    }

    @Override
    public CustomerResponseDTO oAuthRegistration(String email, String name, String providerUserId, AuthProvider provider) {
        return null;
    }

    @Override
    public CustomerResponseDTO updateMobileNumber(Long customerId, String mobileNumber) {
        return null;
    }

    @Override
    public Optional<CustomerResponseDTO> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long customerId) {
        return null;
    }

}
