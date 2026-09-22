package com.flightbookingengineproject.controller;

import com.flightbookingengineproject.entity.Flight;
import com.flightbookingengineproject.entity.PaymentInfo;
import com.flightbookingengineproject.repository.FlightRepository;
import com.flightbookingengineproject.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

// REST Controller
@RestController
// Base URL
@RequestMapping("/api")
public class FlightController {

    // Dependencies
    private final BookingService bookingService;
    private final FlightRepository flightRepository;

    // Constructor Injection
    public FlightController(
            BookingService bookingService,
            FlightRepository flightRepository) {
        this.bookingService = bookingService;
        this.flightRepository = flightRepository;
    }

    // Endpoint 1: GET /api/{id}
    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {

        // Find flight by ID
        Optional<Flight> flightOptional = flightRepository.findById(id);

        // Check if flight exists
        if (flightOptional.isPresent()) {
            return flightOptional.get();
        } else {
            throw new RuntimeException("Flight not found!");
        }
    }

    // Endpoint 2: POST /api/book
    @PostMapping("/book")
    public ResponseEntity<String> bookFlight(
            @RequestBody PaymentInfo paymentInfo) {

        try {
            // Call BookingService
            String result = bookingService.bookFlight(paymentInfo);

            // Return 200 OK
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {

            // Return 500 Internal Server Error
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }
}