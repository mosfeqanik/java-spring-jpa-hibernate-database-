package com.uservalidation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    private int id;

    // 2. @NotNull
    // 3. @Size
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
    private String name;

    // 2. @NotNull
    // 4. @Email
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    public User() {}

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}