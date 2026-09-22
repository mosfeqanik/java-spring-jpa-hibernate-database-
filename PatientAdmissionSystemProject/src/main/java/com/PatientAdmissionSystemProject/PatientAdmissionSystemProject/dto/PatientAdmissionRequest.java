package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PatientAdmissionRequest {

    // Patient name
    @NotBlank(message = "Patient name cannot be blank")
    private String name;

    // Age: 0 - 150
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Invalid age provided")
    private int age;

    // Systolic BP: 50 - 250
    @NotNull(message = "Systolic BP is required")
    @Min(value = 50, message = "Systolic BP too low, please double check input")
    @Max(value = 250, message = "Systolic BP critically high, please double check input")
    private Integer systolicBp;

    // Diastolic BP: 30 - 150
    @NotNull(message = "Diastolic BP is required")
    @Min(value = 30, message = "Diastolic BP too low")
    @Max(value = 150, message = "Diastolic BP too high")
    private Integer diastolicBp;

    // Getters and Setters

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

    public Integer getSystolicBp() {
        return systolicBp;
    }

    public void setSystolicBp(Integer systolicBp) {
        this.systolicBp = systolicBp;
    }

    public Integer getDiastolicBp() {
        return diastolicBp;
    }

    public void setDiastolicBp(Integer diastolicBp) {
        this.diastolicBp = diastolicBp;
    }
}