package com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// TODO: Mark this class as a JPA Entity
@Entity
public class Ticket {

    // TODO: Define the Primary Key 'id'
    @Id
    // TODO: Configure it to Auto-Increment (IDENTITY strategy)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String employeeName;

    // TODO: Map this Enum to the database as a String (not as an Integer)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private LocalDateTime createdAt;

    // CONSTRUCTORS:

    // TODO: Write the Default Constructor (Required by JPA)
    // Challenge: Set the default values when a ticket is first created.
    // 1. Set 'createdAt' to the current time.
    // 2. Set 'status' of a new ticket to 'OPEN' by default.
    public Ticket() {
        this.createdAt = LocalDateTime.now();
        this.status = TicketStatus.OPEN;
    }

    // TODO: Write the parameterized Constructor (to set title, description, and employeeName)
    // Also set 'createdAt' to the current time and 'status' to 'OPEN' in this constructor as well.
    public Ticket(String title, String description, String employeeName) {
        this.title = title;
        this.description = description;
        this.employeeName = employeeName;
        this.createdAt = LocalDateTime.now();
        this.status = TicketStatus.OPEN;
    }

    // TODO: Write the Getters and Setters for all fields
    // You have 2 options:
    // 1. Manually write them
    // 2. Use the lombok library to auto-generate them

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}