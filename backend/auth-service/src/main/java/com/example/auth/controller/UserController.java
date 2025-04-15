package com.example.auth.controller;

import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.response.LoginResponse;
import com.example.auth.entity.UserEntity;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder; 

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        var user = repository.findByUsername(login.getUsername());
        if (user.isPresent() && passwordEncoder.matches(login.getPassword(), user.get().getPassword())) {
            var logged = user.get();
            return ResponseEntity.ok(new LoginResponse("Login OK", logged.getUsername(), logged.getRole()));
        }
        return ResponseEntity.status(401).body(new LoginResponse("Unauthorized", null, null));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    user.setId(null); 
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return ResponseEntity.ok(repository.save(user));
}


    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updated) {
        return repository.findById(id)
            .map(user -> {
                user.setUsername(updated.getUsername());
                user.setPassword(passwordEncoder.encode(updated.getPassword())); 
                user.setRole(updated.getRole());
                return ResponseEntity.ok(repository.save(user));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
