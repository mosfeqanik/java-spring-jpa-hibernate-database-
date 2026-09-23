package com.PayGuardTransactionAPI.Project.service;

import com.PayGuardTransactionAPI.Project.dto.TransactionRequest;
import com.PayGuardTransactionAPI.Project.exception.InsufficientBalanceException;
import com.PayGuardTransactionAPI.Project.exception.SelfTransferException;
import com.PayGuardTransactionAPI.Project.model.Transaction;
import com.PayGuardTransactionAPI.Project.model.TransactionStatus;
import com.PayGuardTransactionAPI.Project.model.TransactionType;
import com.PayGuardTransactionAPI.Project.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

// Mark this class as a Spring Service component
@Service
public class TransactionService {

    // In-memory fake database to simulate account balances
    private final Map<String, BigDecimal> balances = new HashMap<>();

    // Inject TransactionRepository
    private final TransactionRepository repository;

    // Constructor Injection
    public TransactionService(TransactionRepository repository) {

        // Assign injected repository to class field
        this.repository = repository;

        // Pre-fund demo accounts for testing
        balances.put("ACC-100", new BigDecimal("5000"));
        balances.put("ACC-200", new BigDecimal("100"));
    }

    // Main transaction processing logic
    public Transaction process(TransactionRequest request) {

        // 1. Map DTO to Entity
        Transaction transaction = new Transaction();

        transaction.setSourceAccountId(request.getSourceAccountId());
        transaction.setTargetAccountId(request.getTargetAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());

        // Fetch current balance (defaults to 0 if account doesn't exist)
        BigDecimal currentBalance = balances.getOrDefault(
                request.getSourceAccountId(),
                BigDecimal.ZERO
        );

        // 2. Block Invalid Requests First

        // Request A: Prevent self-transfers
        if (request.getType() == TransactionType.TRANSFER
                && request.getSourceAccountId().equals(request.getTargetAccountId())) {

            throw new SelfTransferException("Self transfer not allowed.");
        }

        // Request B: Prevent overdrawing the account
        if ((request.getType() == TransactionType.WITHDRAWAL
                || request.getType() == TransactionType.TRANSFER)
                && currentBalance.compareTo(request.getAmount()) < 0) {

            throw new InsufficientBalanceException("Insufficient balance.");
        }

        // 3. Execute the Math

        // DEPOSIT
        if (request.getType() == TransactionType.DEPOSIT) {

            balances.put(
                    request.getSourceAccountId(),
                    currentBalance.add(request.getAmount())
            );

        // WITHDRAWAL
        } else if (request.getType() == TransactionType.WITHDRAWAL) {

            balances.put(
                    request.getSourceAccountId(),
                    currentBalance.subtract(request.getAmount())
            );

        // TRANSFER
        } else if (request.getType() == TransactionType.TRANSFER) {

            // Subtract from sender
            balances.put(
                    request.getSourceAccountId(),
                    currentBalance.subtract(request.getAmount())
            );

            // Add to receiver
            BigDecimal targetBalance = balances.getOrDefault(
                    request.getTargetAccountId(),
                    BigDecimal.ZERO
            );

            balances.put(
                    request.getTargetAccountId(),
                    targetBalance.add(request.getAmount())
            );
        }

        // 4. Save and Return

        // Set transaction status to COMPLETED
        transaction.setStatus(TransactionStatus.COMPLETED);

        // Save transaction to H2 database
        return repository.save(transaction);
    }
}