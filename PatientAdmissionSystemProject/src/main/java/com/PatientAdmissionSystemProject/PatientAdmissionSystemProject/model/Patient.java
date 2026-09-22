package com.example.demo.model;

import jakarta.persistence.*;

// Mark this class as a JPA Entity
@Entity
// Map this entity to the "patients" table
@Table(name = "patients")
public class Patient {

    // Primary Key
    @Id
    // Auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    // Vital Signs
    private int systolicBp;

    private int diastolicBp;

    // Store enum as STRING
    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    // No-args constructor required by JPA
    public Patient() {

    }

    // Constructor with all fields except id and status
    public Patient(String name, int age, int systolicBp, int diastolicBp) {
        this.name = name;
        this.age = age;
        this.systolicBp = systolicBp;
        this.diastolicBp = diastolicBp;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSystolicBp() {
        return systolicBp;
    }

    public void setSystolicBp(int systolicBp) {
        this.systolicBp = systolicBp;
    }

    public int getDiastolicBp() {
        return diastolicBp;
    }

    public void setDiastolicBp(int diastolicBp) {
        this.diastolicBp = diastolicBp;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }
}