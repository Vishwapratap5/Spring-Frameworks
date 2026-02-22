package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DAO.CustomerRepository;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountCreationRequestDTO;
import com.guru.springsecurity_learning.DTO.AccountDTOs.AccountResponseDTO;
import com.guru.springsecurity_learning.Enums.AccountStatus;
import com.guru.springsecurity_learning.Exception.InvalidOperationException;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Customer;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountNumberGenerationService accountNumberGenerationService;
    private final String defaultBranchCode;
    private final ModelMapper modelMapper;
    private final CurrentUserService currentUserService;

    public AccountServiceImpl(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            AccountNumberGenerationService accountNumberGenerationService,
            @Value("${bank.default.branch-code}") String defaultBranchCode,
            ModelMapper modelMapper, CurrentUserService currentUserService) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountNumberGenerationService = accountNumberGenerationService;
        this.defaultBranchCode = defaultBranchCode;
        this.modelMapper = modelMapper;
        this.currentUserService = currentUserService;
    }




    @Override
    @Transactional
    public AccountResponseDTO createAccount(AccountCreationRequestDTO account) {
        Customer customer = customerRepository.findByCustomerId(account.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        boolean isAccountExist = accountRepository.existsByCustomerAndAccountType(customer, account.getAccountType());
       if(isAccountExist){
           throw new EntityExistsException("Customer with id " + account.getCustomerId() + " already exists with account type " + account.getAccountType());
       }

       Account newAccount=new Account();
       newAccount.setAccountType(account.getAccountType());
       newAccount.setStatus(AccountStatus.ACTIVE);
       newAccount.setAccountNumber(accountNumberGenerationService.generateAccountNumber());
       newAccount.setBranchCode(defaultBranchCode);
       newAccount.setCustomer(customer);
       newAccount.setBalance(account.getInitialDeposit());
       Account savedAccount=accountRepository.save(newAccount);

        return mapToResponse(savedAccount);

    }


    @Override
    @Transactional
    public void freezeAccount(Long accountId) {
        Account account=accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if(account.getStatus().equals(AccountStatus.FROZEN)){
            throw new InvalidOperationException("Account is already FROZEN");
        }
        if(account.getStatus().equals(AccountStatus.CLOSED)){
            throw new InvalidOperationException("Account is already CLOSED");
        }
        account.setStatus(AccountStatus.FROZEN);
        accountRepository.save(account);
    }


    @Override
    @Transactional
    public void unfreezeAccount(Long accountId) {
        Account account=accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if(account.getStatus().equals(AccountStatus.ACTIVE)){
            throw new InvalidOperationException("Account is already Active");
        }
        if(account.getStatus().equals(AccountStatus.CLOSED)){
            throw new InvalidOperationException("Account is already CLOSED");
        }
        account.setStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }


    @Override
    @Transactional
    public void closeAccount(Long accountId) {
        Account account=accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if(account.getStatus().equals(AccountStatus.CLOSED)){
            throw new InvalidOperationException("Account is already CLOSED");
        }

        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidOperationException("Account balance must be zero to close");
        }

        account.setStatus(AccountStatus.CLOSED);
        accountRepository.save(account);
    }


    @Override
    public List<AccountResponseDTO> myAccounts() {

        Customer currentCustomer = currentUserService.getCurrentCustomer();
        List<Account> accounts=accountRepository.findByCustomer(currentCustomer);
        if(accounts.isEmpty()){
            throw new EntityNotFoundException("You don't have any accounts..! please create one..");
        }
        return accounts.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long accountId) {
        Customer currentCustomer = currentUserService.getCurrentCustomer();
        Account account=accountRepository.findByIdAndCustomer(accountId,currentCustomer).orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return mapToResponse(account);
    }


    private AccountResponseDTO mapToResponse(Account account) {
        String accNo = account.getAccountNumber();
        String masked = "XXXX-XXXX-" + accNo.substring(accNo.length() - 4);

        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountNumber(masked);
        dto.setAccountType(account.getAccountType());
        dto.setBranchCode(account.getBranchCode());
        dto.setAccountStatus(account.getStatus());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setCustomerId(account.getCustomer().getCustomerId());
        return dto;
    }
}
