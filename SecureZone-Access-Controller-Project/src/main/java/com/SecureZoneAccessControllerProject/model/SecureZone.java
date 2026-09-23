package com.SecureZoneAccessControllerProject.model;

import jakarta.persistence.*;

// Mark this class as a JPA Entity
@Entity
public class SecureZone {

    // Primary Key
    @Id
    // Auto-Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneName;

    // Clearance level required to access this zone
    private String clearanceLevel;

    // No-args constructor required by JPA
    public SecureZone() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(String clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }
}