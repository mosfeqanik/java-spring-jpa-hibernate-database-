package com.PayGuardTransactionAPI.Project.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// TODO: Mark this class as a JPA Entity
@Entity
// TODO: Specify the database table name as "transactions"
@Table(name = "transactions")
public class Transaction {

    // TODO: Define the Primary Key 'id'
    @Id
    // TODO: Configure it to Auto-Increment (IDENTITY strategy)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceAccountId;
    private String targetAccountId;
    private BigDecimal amount;

    // TODO: Map this Enum to the database as a String (so it saves "TRANSFER" instead of "0")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    // TODO: Map this Enum to the database as a String as well
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private LocalDateTime timestamp;

    // CONSTRUCTOR:

    // TODO: Write the Default Constructor (Required by JPA)
    // Challenge: Set the default value when a transaction object is created.
    // 1. Set 'timestamp' to the exact current time
    
    // Default Constructor (Required by JPA)
    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}


