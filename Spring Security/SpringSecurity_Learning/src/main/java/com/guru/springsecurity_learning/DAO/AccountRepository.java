package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Enums.AccountType;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.persistence.LockModeType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {



    boolean existsByCustomerAndAccountType(Customer existingCustomer, @NotNull(message = "Account type is required") AccountType accountType);

    Page<Account> findByCustomer(Customer customer, Pageable pageable);

    Optional<Account> findByIdAndCustomer(Long id, Customer customer);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findByIdAndCustomerForUpdate(Long accountId, Customer currentCustomer);

}