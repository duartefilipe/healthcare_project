package com.example.auth.controller;

import com.example.auth.dto.request.LoginRequest;
import com.example.auth.entity.UserEntity;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        Optional<UserEntity> existingUser = repository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            UserEntity found = existingUser.get();
            found.setPassword(passwordEncoder.encode(user.getPassword())); 
            found.setRole(user.getRole()); 
            return ResponseEntity.ok(repository.save(found));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(repository.save(user));
    }

    @GetMapping("/secure")
    public ResponseEntity<String> securePing(Authentication auth) {
        return ResponseEntity.ok("Hello " + auth.getName() + ", you're authenticated!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = repository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.ok(
            Map.of(
                "username", user.getUsername(),
                "role", user.getRole()
            )
        );
    }
}
