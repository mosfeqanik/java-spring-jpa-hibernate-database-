package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id)
                );
    }

    // Update user email
  @PutMapping("/{id}/email")
    public User updateUserEmail(@PathVariable Long id, @RequestBody User updatedUser) {
        System.out.println("id id ididiiididid"+id);

        // Step 1: Find user by id
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
    UserNotFoundException exception =
            new UserNotFoundException("User not found with id: " + id);

    System.out.println("EXCEPTION MESSAGE = " + exception.getMessage());

    return exception;
});
        // Step 2: Update email
        user.setEmail(updatedUser.getEmail());

        // Step 3: Save updated user
        return userRepository.save(user);
    }
}