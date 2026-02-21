package com.guru.springsecurity_learning;

import com.guru.springsecurity_learning.DAO.AuthorityRepository;
import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.Enums.AuthProvider;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.Authority;
import com.guru.springsecurity_learning.Model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuperAdminBootstrap {

    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void createSuperAdminIfMissing() {

        String email = "superadmin@system.com";

        Customer superAdmin = customerRepository.findByEmail(email)
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setCustomerName("Super Admin");
                    c.setEmail(email);
                    c.setPassword(passwordEncoder.encode("ChangeMe@123"));
                    c.setAuthProvider(AuthProvider.LOCAL);
                    return customerRepository.save(c);
                });

        boolean hasSuperAdmin =
                authorityRepository.existsByCustomerAndRole(
                        superAdmin, Role.ROLE_SUPER_ADMIN);

        if (!hasSuperAdmin) {
            Authority authority = new Authority();
            authority.setCustomer(superAdmin);
            authority.setRole(Role.ROLE_SUPER_ADMIN);
            authorityRepository.save(authority);
        }
    }
}