package com.PayGuardTransactionAPI.Project.controller;

import com.PayGuardTransactionAPI.Project.dto.TransactionRequest;
import com.PayGuardTransactionAPI.Project.model.Transaction;
import com.PayGuardTransactionAPI.Project.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Mark this class as a REST Controller
@RestController
// Set the base routing path
@RequestMapping("/api")
public class TransactionController {

    // Inject TransactionService via constructor injection
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Handle POST /api/process
    @PostMapping("/process")
    // Return HTTP 201 CREATED
    @ResponseStatus(HttpStatus.CREATED)
    // Validate DTO and extract JSON request body
    public Transaction processTransaction(
            @Valid @RequestBody TransactionRequest request) {

        // Pass validated request to service
        return transactionService.process(request);
    }
}