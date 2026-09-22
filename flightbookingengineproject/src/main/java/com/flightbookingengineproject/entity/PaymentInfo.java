package com.flightbookingengineproject.entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

// TODO: Mark this class as a JPA Entity so it maps to a database table
@Entity
@Getter
@Setter
public class PaymentInfo {

    // Primary Key
    @Id
    // Auto-Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String passengerName;
    private double amount;

    // Links payment to flight
    private Long flightId;

    // No-Argument Constructor (Required by JPA)
    public PaymentInfo() {
        
    }

    // All-Arguments Constructor
    public PaymentInfo(Long id, String passengerName, double amount, Long flightId) {
        this.id = id;
        this.passengerName = passengerName;
        this.amount = amount;
        this.flightId = flightId;
    }
}


