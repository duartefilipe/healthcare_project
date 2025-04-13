package com.example.patient.controller;

import com.example.patient.dto.request.PatientRequest;
import com.example.patient.dto.response.PatientResponse;
import com.example.patient.entity.PatientEntity;
import com.example.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository repository;

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody PatientRequest request) {
        PatientEntity saved = repository.save(PatientEntity.builder()
                .name(request.getName())
                .document(request.getDocument())
                .birthDate(request.getBirthDate())
                .build());

        return ResponseEntity.ok(toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> list() {
        List<PatientResponse> responses = repository.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    private PatientResponse toResponse(PatientEntity entity) {
        return PatientResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .document(entity.getDocument())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
