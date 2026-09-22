package com.flightbookingengineproject.entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

// TODO: Mark this class as a JPA Entity so it maps to a database table named 'Flight'
@Entity
@Getter
@Setter
public class Flight {

    // TODO: Define the Primary Key
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String flightNumber;
    private String destination;
    private int availableSeats;

    // Create a No-Argument Constructor (Required by JPA)
    public Flight() {
        
    }

    // All-Arguments Constructor
    public Flight(Long id, String flightNumber, String destination, int availableSeats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }


}