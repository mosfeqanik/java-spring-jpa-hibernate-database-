package com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.controller;

import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.exception.InvalidTicketStateException;
import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.model.Ticket;
import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.model.TicketStatus;
import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Mark this class as a REST Controller
@RestController

// Base routing path
@RequestMapping("/api")
public class TicketController {

    private final TicketRepository ticketRepository;

    // Constructor Injection
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // GET /api
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAllByOrderByCreatedAtDesc();
    }

    // GET /api/open
    @GetMapping("/open")
    public List<Ticket> getOpenTickets() {
        return ticketRepository.findByStatusOrderByCreatedAtDesc(TicketStatus.OPEN);
    }

    // POST /api
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // POST /api/{id}/resolve
    @PostMapping("/{id}/resolve")
    public ResponseEntity<Ticket> resolveTicket(@PathVariable Long id) {

        // Find ticket by ID
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        // Check if ticket exists
        if (optionalTicket.isPresent()) {

            Ticket ticket = optionalTicket.get();

            // Prevent resolving an already resolved ticket
            if (ticket.getStatus() == TicketStatus.RESOLVED) {
                throw new InvalidTicketStateException(
                    "Ticket is already resolved and cannot be closed again."
                );
            }

            // Change status to RESOLVED
            ticket.setStatus(TicketStatus.RESOLVED);

            // Save updated ticket
            Ticket updatedTicket = ticketRepository.save(ticket);

            // Return 200 OK
            return ResponseEntity.ok(updatedTicket);

        } else {

            // Return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}