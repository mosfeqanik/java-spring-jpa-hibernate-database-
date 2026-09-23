package com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.repository;

import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.model.Ticket;
import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Mark this interface as a Spring Data Repository component
@Repository

// Extend JpaRepository with Entity = Ticket, Primary Key = Long
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Fetch ALL tickets, newest first
    List<Ticket> findAllByOrderByCreatedAtDesc();

    // Fetch tickets by status, newest first
    List<Ticket> findByStatusOrderByCreatedAtDesc(TicketStatus status);
}