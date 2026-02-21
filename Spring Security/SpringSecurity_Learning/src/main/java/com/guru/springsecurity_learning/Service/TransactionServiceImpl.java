package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DAO.TransactionRepository;
import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.Transaction;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CurrentUserService currentUserService;

    @Override
    public List<TransactionResponseDTO> getAllTransactions(Long accountId) {
        Customer currentCustomer =currentUserService.getCurrentCustomer();
        Account account=accountRepository.findByIdAndCustomer(accountId,currentCustomer).orElseThrow(()->new EntityNotFoundException("Account not found"));
        List<Transaction> transactions=transactionRepository.findByAccount_IdOrderByTransactionDateDesc(account.getId());
        return transactions.stream().map(transaction->modelMapper.map(transaction,TransactionResponseDTO.class)).toList();
    }
}
