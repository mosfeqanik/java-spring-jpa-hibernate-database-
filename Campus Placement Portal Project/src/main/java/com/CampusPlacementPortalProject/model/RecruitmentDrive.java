package com.CampusPlacementPortalProject.model;

import jakarta.persistence.*;

// TODO: Mark this class as a JPA Entity so Spring Boot maps it to a database table
@Entity
public class RecruitmentDrive {

    // TODO: Define the Primary Key 'id'
    @Id
    // TODO: Configure it to Auto-Increment (IDENTITY strategy)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String role;

    // We use Double to allow decimal package values (e.g., 8.5 LPA)
    private Double packageLPA;

    // CONSTRUCTORS:

    // Default Constructor (Strictly required by JPA)
    public RecruitmentDrive() {
    }

    // TODO: Write the Parameterized Constructor (to easily set companyName, role, and packageLPA)
    public RecruitmentDrive(String companyName, String role, Double packageLPA) {
        this.companyName = companyName;
        this.role = role;
        this.packageLPA = packageLPA;
    }

    // GETTERS AND SETTERS:

    // TODO: Write or generate the Getters and Setters for all fields
    // Hint: You can write these manually or use Lombok

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getPackageLPA() {
        return packageLPA;
    }

    public void setPackageLPA(Double packageLPA) {
        this.packageLPA = packageLPA;
    }

}


