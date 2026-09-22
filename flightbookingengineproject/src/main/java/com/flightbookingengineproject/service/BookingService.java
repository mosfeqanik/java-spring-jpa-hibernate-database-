package com.flightbookingengineproject.service;

import com.flightbookingengineproject.entity.Flight;
import com.flightbookingengineproject.entity.PaymentInfo;
import org.springframework.transaction.annotation.Transactional;
import com.flightbookingengineproject.repository.FlightRepository;
import com.flightbookingengineproject.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookingService {

    // Repository dependencies
    private final FlightRepository flightRepository;
    private final PaymentRepository paymentRepository;

    // Constructor Injection
    public BookingService(
            FlightRepository flightRepository,
            PaymentRepository paymentRepository) {
        this.flightRepository = flightRepository;
        this.paymentRepository = paymentRepository;
    }

    // Seat update + payment save happen in one transaction
    @Transactional
    public String bookFlight(PaymentInfo paymentInfo) {

        // 1: Fetch Flight info
        Optional<Flight> flightOptional =
                flightRepository.findById(paymentInfo.getFlightId());

        Flight flight;

        // 2: Handle Optional
        if (flightOptional.isPresent()) {
            flight = flightOptional.get();
        } else {
            throw new RuntimeException("Flight not found!");
        }

        // 3: Validate Seats
        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Flight is fully booked!");
        }

        // 4: Deduct Seat
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);

        // Save updated flight
        flightRepository.save(flight);

        // 5: Validate Payment
        if (paymentInfo.getAmount() > 500) {
            throw new RuntimeException(
                    "Payment Failed: Limit Exceeded. Transaction Rolled Back.");
        }

        // 6: Save Payment
        paymentRepository.save(paymentInfo);

        return "Booking Confirmed for " + paymentInfo.getPassengerName();
    }
}