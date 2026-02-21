package com.guru.springsecurity_learning.Service;


import com.guru.springsecurity_learning.DAO.AuthorityRepository;
import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.DAO.RoleAuditRepository;
import com.guru.springsecurity_learning.Enums.AuditAction;
import com.guru.springsecurity_learning.Enums.Role;
import com.guru.springsecurity_learning.Model.Authority;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.RoleAudit;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdminPanelImpl implements SuperAdminPanelService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    private RoleAuditRepository roleAuditRepository;


    @Override
    @Transactional
    public void assignRole(Long customerId, Role role) {


        Customer foundCustomer=customerRepository.findByCustomerId(customerId).orElseThrow(()->new EntityNotFoundException("Customer not found"));
        boolean customerExists = authorityRepository.existsByCustomerAndRole(foundCustomer,role);
        if(!customerExists){
            Authority authority=new Authority();
            authority.setRole(role);
            authority.setCustomer(foundCustomer);
            authorityRepository.save(authority);

            //Auditing
           Long currentUserId=getCurrentAuthenticatedCustomer();
            RoleAudit roleAudit=new RoleAudit();
            roleAudit.setRole(role);
            roleAudit.setAction(AuditAction.ASSIGN);
            roleAudit.setTimestamp(LocalDateTime.now());
            roleAudit.setTargetCustomerId(customerId);
            roleAudit.setPerformedByCustomerId(currentUserId);
            roleAuditRepository.save(roleAudit);


        }else{
            throw new EntityExistsException("Customer With ID "+customerId+" and Current Role "+role +"already exists..");
        }

    }

    @Override
    @Transactional
    public void deleteRole(Long customerId, Role role) {

        Customer foundCustomer=customerRepository.findById(customerId).orElseThrow(()->new EntityNotFoundException("Customer not found"));
        boolean hasRole = authorityRepository.existsByCustomerAndRole(foundCustomer, role);

        if (!hasRole) {
            throw new IllegalStateException(
                    "Customer does not have role " + role
            );
        }

        Long currentUserId = getCurrentAuthenticatedCustomer();
        if (currentUserId.equals(customerId)
                && role == Role.ROLE_SUPER_ADMIN) {
            throw new IllegalStateException(
                    "SUPER_ADMIN cannot remove own role"
            );
        }

        if (role == Role.ROLE_SUPER_ADMIN) {
            long count =
                    authorityRepository.countByRole(Role.ROLE_SUPER_ADMIN);

            if (count <= 1) {
                throw new IllegalStateException(
                        "Cannot remove the last SUPER_ADMIN"
                );
            }
        }
        authorityRepository.deleteByCustomerAndRole(foundCustomer, role);

        //auditing

        RoleAudit roleAudit=new RoleAudit();
        roleAudit.setRole(role);
        roleAudit.setAction(AuditAction.REMOVE);
        roleAudit.setTimestamp(LocalDateTime.now());
        roleAudit.setTargetCustomerId(customerId);
        roleAudit.setPerformedByCustomerId(currentUserId);
        roleAuditRepository.save(roleAudit);

    }

    private Long getCurrentAuthenticatedCustomer() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        Customer customer= customerRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Logged-in user not found"));

        return  customer.getCustomerId();
    }

}
