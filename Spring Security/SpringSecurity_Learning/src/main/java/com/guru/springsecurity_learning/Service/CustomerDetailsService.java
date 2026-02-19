package com.guru.springsecurity_learning.Service;


import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer foundCustomer= customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Customer "+username+" not found"));
        List<GrantedAuthority> grantedAuthorities = foundCustomer.getAuthorities().stream()
                                                                                  .map((role)-> new  SimpleGrantedAuthority(role.getName()))
                                                                                  .collect(Collectors.toList());

        return User.withUsername(foundCustomer.getEmail())
                   .password(foundCustomer.getPassword())
                   .authorities(grantedAuthorities).build();
    }

}
