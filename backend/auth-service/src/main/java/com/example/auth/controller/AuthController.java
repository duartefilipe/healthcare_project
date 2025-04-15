package com.example.auth.controller;

import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.UserRequest;
import com.example.auth.entity.UserEntity;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        UserEntity user = UserEntity.builder()
            .name(request.getName())
            .document(request.getDocument())
            .birthDate(request.getBirthDate())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
        return ResponseEntity.ok(repository.save(user));
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
                "role", user.getRole(),
                "id", user.getId()
            )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication auth) {
        return ResponseEntity.ok(auth.getName());
    }

    @GetMapping("/listMedicals")
    public ResponseEntity<List<UserEntity>> listUsers(@RequestParam(required = false) String role) {
        if (role != null && !role.isBlank()) {
            List<UserEntity> result = repository.findByRole(role);
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(repository.findAll());
    }
}