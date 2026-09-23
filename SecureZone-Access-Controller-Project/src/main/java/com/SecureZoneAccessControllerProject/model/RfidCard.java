package com.SecureZoneAccessControllerProject.model;

import jakarta.persistence.*;
import java.time.LocalDate;

// TODO: Mark this class as a JPA Entity
@Entity
public class RfidCard {
    
    // TODO: Define the Primary Key
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // TODO: Configure the column properties
    // Challenge 1: The card number must be unique across the database.
    // Challenge 2: The card number cannot be null.
    @Column(nullable = false, unique = true)
    private String cardNumber;
    
    private LocalDate issueDate;

    // CONSTRUCTORS:
    // No-args constructor is required by JPA
    public RfidCard() {

    }

    // TODO: Create a constructor that initializes all fields except 'id' (since it's auto-generated)
   public RfidCard(String cardNumber, LocalDate issueDate) {
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
    }
    // TODO: Write the Getters and Setters for all fields
    // You have 2 options: 
    // 1. Manually write them
    // 2. Add Lombok annotations at the top of the class to generate them automatically

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
}


