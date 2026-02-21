package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerLoginRequestDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerLoginResponseDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerRegisterRequestDTO;
import com.guru.springsecurity_learning.DTO.CustomerDTOs.CustomerResponseDTO;
import com.guru.springsecurity_learning.Service.CustomerAuthServiceImpl;
import com.guru.springsecurity_learning.Service.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class CustomerAuthController {

    @Autowired
    private CustomerAuthServiceImpl customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth/register")
    public ResponseEntity<CustomerResponseDTO> register(@Valid @RequestBody CustomerRegisterRequestDTO customer){
        CustomerResponseDTO newCustomer=customerService.localCustomerRegistration(customer);
        return new  ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<CustomerLoginResponseDTO> login(@Valid @RequestBody CustomerLoginRequestDTO request){
        Authentication authRequest =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),   // principal
                        request.getPassword()    // credentials (RAW!)
                );

        Authentication authenticated=authenticationManager.authenticate(authRequest);
        String jwt=jwtService.generateToken(authenticated);

        CustomerLoginResponseDTO loginResponse=new CustomerLoginResponseDTO(jwt,"jwt", jwtService.getJwtExpiration());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .body(loginResponse);
    }
}
