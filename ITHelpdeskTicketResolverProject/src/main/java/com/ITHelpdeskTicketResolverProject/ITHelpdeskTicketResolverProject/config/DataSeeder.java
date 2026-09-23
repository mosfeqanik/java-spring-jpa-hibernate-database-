package com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.config;

import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.model.Ticket;
import com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(TicketRepository repository) {
        return args -> {
            repository.save(new Ticket("VPN Connection Failed", "Cannot connect to the corporate VPN from home.", "Ankit Srivastava"));
            repository.save(new Ticket("Monitor Flickering", "My secondary monitor keeps turning off and on.", "Bob Jones"));
            repository.save(new Ticket("Software License", "Need an IntelliJ IDEA Ultimate license for the new project.", "Anuj Kumar"));
        };
    }
}

