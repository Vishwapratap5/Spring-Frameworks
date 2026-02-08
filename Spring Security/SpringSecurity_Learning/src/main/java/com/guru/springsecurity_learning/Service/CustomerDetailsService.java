package com.guru.springsecurity_learning.Service;


import com.guru.springsecurity_learning.DAO.CustomerRepo;
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

@Service
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer foundCustomer=customerRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Customer "+username+" not found"));
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+foundCustomer.getRole());

        return User.withUsername(foundCustomer.getEmail())
                   .password(foundCustomer.getPassword())
                   .authorities(grantedAuthority).build();
    }
}
