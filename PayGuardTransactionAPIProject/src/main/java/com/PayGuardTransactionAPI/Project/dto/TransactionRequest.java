package com.PayGuardTransactionAPI.Project.dto;

import com.PayGuardTransactionAPI.Project.model.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import jakarta.validation.constraints.Digits;

public class TransactionRequest {

    // Source Account ID cannot be null, empty, or just spaces
    @NotBlank(message = "Source Account ID cannot be empty.")
    private String sourceAccountId;

    // Target Account can be null
    private String targetAccountId;

    // Amount must be provided
    @NotNull(message = "Transaction amount is required.")
    
    // Amount must be greater than zero
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero.")
    
    // Maximum 10 integer digits and 2 fraction digits
    @Digits(integer = 10, fraction = 2, message = "Invalid amount format.")
    private BigDecimal amount;

    // Transaction type must be provided
    @NotNull(message = "Transaction type must be provided (DEPOSIT, WITHDRAWAL, TRANSFER).")
    private TransactionType type;

    // GETTERS AND SETTERS

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}