package com.uservalidation.controller;

import com.uservalidation.model.User;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    List<User> users = new ArrayList<>();

    // CREATE
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {

        users.add(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // READ ALL
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(users);
    }
}