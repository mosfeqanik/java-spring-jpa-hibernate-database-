package com.SecureZoneAccessControllerProject.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Mark this class as a JPA Entity
@Entity
public class Employee {

    // Define the Primary Key
    @Id
    // Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;

    // ONE-TO-ONE

    // One Employee has exactly One RfidCard
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
        name = "rfid_card_id",
        referencedColumnName = "id"
    )
    private RfidCard rfidCard;

    // MANY-TO-MANY

    // Many Employees can access Many SecureZones
    @ManyToMany
    @JoinTable(
        name = "employee_zones",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "zone_id")
    )
    private Set<SecureZone> allowedZones = new HashSet<>();

    // Helper method
    public void addZone(SecureZone zone) {
        allowedZones.add(zone);
    }

    // No-args constructor required by JPA
    public Employee() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public RfidCard getRfidCard() {
        return rfidCard;
    }

    public void setRfidCard(RfidCard rfidCard) {
        this.rfidCard = rfidCard;
    }

    public Set<SecureZone> getAllowedZones() {
        return allowedZones;
    }

    public void setAllowedZones(Set<SecureZone> allowedZones) {
        this.allowedZones = allowedZones;
    }
}