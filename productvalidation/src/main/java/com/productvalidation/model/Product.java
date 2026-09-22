package com.productvalidation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
public class Product {

    private int id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
    private String name;

    @NotNull(message = "Brand cannot be null")
    @NotBlank(message = "Brand cannot be blank")
    @Size(min = 2, message = "Brand must be at least 2 characters")
    private String brand;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String supplierEmail;

    public Product() {}

    public Product(int id, String name, String brand, String supplierEmail) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.supplierEmail = supplierEmail;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getSupplierEmail() { return supplierEmail; }
    public void setSupplierEmail(String supplierEmail) { this.supplierEmail = supplierEmail; }
}