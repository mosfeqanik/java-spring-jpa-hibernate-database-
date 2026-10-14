package com.CampusPlacementPortalProject.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// TODO 1: Mark this class as a Spring Configuration class
@Configuration
// TODO 2: Enable Spring Security for web applications
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for simplicity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                // Public Routes (Frontend UI & H2 Console) - Pre-populated for you!
                .requestMatchers("/", "/index.html", "/favicon.ico", "/error", "/h2-console/**").permitAll()
                
                // ------------------------------------------------------------------------
                // TODO 3: Implement Route Authorization (RBAC)
                // ------------------------------------------------------------------------
                // 1. Allow both "STUDENT" and "PLACEMENT_OFFICER" to access "/api/view" endpoint
                // 2. Restrict "/api/manage/**" so ONLY "PLACEMENT_OFFICER" can access it
                // 3. Require authentication for any other request
                
                .requestMatchers("/api/view").hasAnyRole("STUDENT", "PLACEMENT_OFFICER")
                .requestMatchers("/api/manage/**").hasRole("PLACEMENT_OFFICER")
                .anyRequest().authenticated()

            )

                // Allow H2 Console to render in browser iframe (Pre-populated)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) 
                
                // Prevent native browser login popup for Javascript fetch() API (Pre-populated)
                .httpBasic(basic -> basic.authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized access");
                }));

        return http.build();
    }

    // TODO 4: Create a Bean to manage our In-Memory Users
    @Bean
    public UserDetailsService userDetailsService() {

        // ------------------------------------------------------------------------
        // TODO 5: Create In-Memory Users using User.builder()
        // REMEMBER: Prefix all passwords with {noop} so Spring knows they are plain text!
        // ------------------------------------------------------------------------

        // Admin 1: Alice (Role: PLACEMENT_OFFICER, username: alice, Password: admin123)
        UserDetails adminAlice = User.builder()
                .username("alice")
                .password("{noop}admin123")
                .roles("PLACEMENT_OFFICER")
                .build();

        // Admin 2: Bob (Role: PLACEMENT_OFFICER, username: bob, Password: admin123)
        UserDetails adminBob = User.builder()
                .username("bob")
                .password("{noop}admin123")
                .roles("PLACEMENT_OFFICER")
                .build();

        // Student User (Role: STUDENT, username: student_user, Password: pass123)
        UserDetails student = User.builder()
                .username("student_user")
                .password("{noop}pass123")
                .roles("STUDENT")
                .build();

        // Return the created users to Spring Security's memory manager
        return new InMemoryUserDetailsManager(adminAlice, adminBob, student);
    }
}


