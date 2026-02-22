package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DAO.TransactionRepository;
import com.guru.springsecurity_learning.DTO.TransactionDTO.TransactionResponseDTO;
import com.guru.springsecurity_learning.Enums.AccountStatus;
import com.guru.springsecurity_learning.Enums.TransactionStatus;
import com.guru.springsecurity_learning.Enums.TransactionType;
import com.guru.springsecurity_learning.Exception.InvalidAmountException;
import com.guru.springsecurity_learning.Exception.InvalidOperationException;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.Transaction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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


    @Override
    @Transactional
    public TransactionResponseDTO debit(Long accountId, BigDecimal amount,String transactionRef) {

        Optional<Transaction> existing =
                transactionRepository.findByTransactionRef(transactionRef);

        if (existing.isPresent()) {
            return modelMapper.map(existing.get(),TransactionResponseDTO.class);
        }

        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        Customer currentCustomer=currentUserService.getCurrentCustomer();
        Account account = accountRepository.findByIdAndCustomerForUpdate(accountId,currentCustomer)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if(account.getStatus()!=AccountStatus.ACTIVE){
            throw new InvalidOperationException("You can't debit from this account because: account is"+account.getStatus());
        }

        BigDecimal balanceBefore = account.getBalance();

        if(balanceBefore.compareTo(BigDecimal.ZERO)<=0 || balanceBefore.compareTo(amount)<0){
           Transaction failedTransaction = new Transaction();
           failedTransaction.setAccount(account);
           failedTransaction.setTransactionAmount(amount);
           failedTransaction.setTransactionRef(transactionRef);
           failedTransaction.setTransactionType(TransactionType.WITHDRAWAL);
           failedTransaction.setBalanceBefore(balanceBefore);
           failedTransaction.setBalanceAfter(balanceBefore);
           failedTransaction.setTransactionStatus(TransactionStatus.FAILED);
           transactionRepository.save(failedTransaction);
           return modelMapper.map(failedTransaction,TransactionResponseDTO.class);
        }

        BigDecimal balanceAfter = account.getBalance().subtract(amount);
        account.setBalance(balanceAfter);

        Transaction successfulTransaction = new Transaction();
        successfulTransaction.setAccount(account);
        successfulTransaction.setTransactionAmount(amount);
        successfulTransaction.setTransactionRef(transactionRef);
        successfulTransaction.setTransactionType(TransactionType.WITHDRAWAL);
        successfulTransaction.setBalanceBefore(balanceBefore);
        successfulTransaction.setBalanceAfter(balanceAfter);
        successfulTransaction.setTransactionStatus(TransactionStatus.SUCCESS);
        accountRepository.save(account);
        transactionRepository.save(successfulTransaction);
        return modelMapper.map(successfulTransaction,TransactionResponseDTO.class);
    }



    @Override
    @Transactional
    public TransactionResponseDTO credit(Long accountId, BigDecimal amount,String transactionRef){

        Optional<Transaction> existing = transactionRepository.findByTransactionRef(transactionRef);

        if (existing.isPresent()) {
            return modelMapper.map(existing.get(),TransactionResponseDTO.class);
        }
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        Customer currentCustomer=currentUserService.getCurrentCustomer();
        Account account = accountRepository.findByIdAndCustomerForUpdate(accountId,currentCustomer)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        if(account.getStatus()!=AccountStatus.ACTIVE){
            throw new InvalidOperationException("You can't debit from this account because: account is"+account.getStatus());
        }

        BigDecimal balanceBefore = account.getBalance();
        BigDecimal balanceAfter = balanceBefore.add(amount);

        account.setBalance(balanceAfter);

        Transaction txn = Transaction.builder()
                .account(account)
                .transactionType(TransactionType.DEPOSIT)
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionAmount(amount)
                .balanceBefore(balanceBefore)
                .balanceAfter(balanceAfter)
                .transactionRef(transactionRef)
                .build();

        accountRepository.save(account);
        transactionRepository.save(txn);

        return modelMapper.map(txn,TransactionResponseDTO.class);
    }


    @Override
    public TransactionResponseDTO getByTransactionRef(String transactionRef) {

        Transaction txn = transactionRepository
                .findByTransactionRef(transactionRef)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        // ownership check
        Customer currentCustomer = currentUserService.getCurrentCustomer();
        if (!txn.getAccount().getCustomer().getCustomerId()
                .equals(currentCustomer.getCustomerId())) {
            throw new IllegalStateException("Access denied");
        }

        return modelMapper.map(txn,TransactionResponseDTO.class);
    }
}
