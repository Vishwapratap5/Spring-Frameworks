package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.AccountRepository;
import com.guru.springsecurity_learning.DAO.TransactionRepository;
import com.guru.springsecurity_learning.Enums.AccountStatus;
import com.guru.springsecurity_learning.Enums.TransactionStatus;
import com.guru.springsecurity_learning.Enums.TransactionType;
import com.guru.springsecurity_learning.Model.Account;
import com.guru.springsecurity_learning.Model.Customer;
import com.guru.springsecurity_learning.Model.Transaction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferTransactionServiceImpl
        implements TransferTransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional
    public void transfer(
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount,
            String transactionRef
    ) {

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to same account");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }

        // 🔁 Idempotency FIRST
        if (transactionRepository.existsByTransactionRef(transactionRef)) {
            return; // already processed
        }

        Customer currentCustomer = currentUserService.getCurrentCustomer();

        // 🔒 Lock order matters (avoid deadlocks)
        Long firstLock = Math.min(fromAccountId, toAccountId);
        Long secondLock = Math.max(fromAccountId, toAccountId);

        Account first = accountRepository.findById(firstLock)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Account second = accountRepository.findById(secondLock)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Account fromAccount =
                fromAccountId.equals(first.getId()) ? first : second;

        Account toAccount =
                toAccountId.equals(first.getId()) ? first : second;

        // 🔐 Ownership check
        if (!fromAccount.getCustomer().getCustomerId()
                .equals(currentCustomer.getCustomerId())) {
            throw new SecurityException("Unauthorized transfer");
        }

        // 🔐 Status checks
        if (fromAccount.getStatus() != AccountStatus.ACTIVE ||
                toAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account not active");
        }

        // 💰 Balance check
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }

        // 💥 Perform transfer
        BigDecimal fromBefore = fromAccount.getBalance();
        BigDecimal toBefore   = toAccount.getBalance();

        fromAccount.setBalance(fromBefore.subtract(amount));
        toAccount.setBalance(toBefore.add(amount));

        // 🧾 Ledger entries
        Transaction debitTxn = Transaction.builder()
                .account(fromAccount)
                .transactionType(TransactionType.TRANSFER_DEBIT)
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionAmount(amount)
                .balanceBefore(fromBefore)
                .balanceAfter(fromAccount.getBalance())
                .transactionRef(transactionRef)
                .build();

        Transaction creditTxn = Transaction.builder()
                .account(toAccount)
                .transactionType(TransactionType.TRANSFER_CREDIT)
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionAmount(amount)
                .balanceBefore(toBefore)
                .balanceAfter(toAccount.getBalance())
                .transactionRef(transactionRef)
                .build();

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionRepository.save(debitTxn);
        transactionRepository.save(creditTxn);
    }
}
