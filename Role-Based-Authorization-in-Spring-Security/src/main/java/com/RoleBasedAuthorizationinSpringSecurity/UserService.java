package com.RoleBasedAuthorizationinSpringSecurity;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private Map<String, UserDetails> users = new HashMap<>();
    private final PasswordEncoder encoder;

    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;

        // 🔥 DEFAULT USERS
        registerUser("user", "1234"); // USER
        registerAdmin("admin", "admin123"); // ADMIN
    }

    // USER ROLE
    public void registerUser(String username, String password) {
        UserDetails user = User.withUsername(username)
                .password(encoder.encode(password))
                .roles("USER")
                .build();

        users.put(username, user);
    }

    // ADMIN ROLE
    public void registerAdmin(String username, String password) {
        UserDetails admin = User.withUsername(username)
                .password(encoder.encode(password))
                .roles("ADMIN")
                .build();

        users.put(username, admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("User not found");
        }
        return users.get(username);
    }
}