package com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

/*********************************************************************
 * DO NOT MODIFY THIS FILE                               
 * *******************************************************************
 * It contains the necessary Spring Security configuration to:
 * 1. Disable CSRF (Cross-Site Request Forgery).
 * 2. Allow unrestricted access to the H2 Database Console and API endpoints.
 * 3. Enable the default Spring Security HTML Form Login.
 * * You do NOT need to write any code in this file. 
 * * *****************************************************************/

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF globally
            .csrf(csrf -> csrf.disable())
            
            // Configure Authorization Rules
            .authorizeHttpRequests(auth -> auth
                // Explicitly ALLOW all traffic to the H2 console without logging in
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/error").permitAll()
                
                // Lock down EVERY other endpoint
                .anyRequest().authenticated()
            )

            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            // Enable the default Spring Security HTML Login Form
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
    }
}