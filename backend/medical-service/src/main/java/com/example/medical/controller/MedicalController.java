package com.example.medical.controller;

import com.example.medical.dto.request.MedicalRequest;
import com.example.medical.dto.response.MedicalResponse;
import com.example.medical.entity.MedicalEntity;
import com.example.medical.repository.MedicalRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/medicals")
@RequiredArgsConstructor
public class MedicalController {

    private final MedicalRepository repository;

    @PostMapping
    public ResponseEntity<MedicalResponse> create(@Valid @RequestBody MedicalRequest request) {
        MedicalEntity saved = repository.save(MedicalEntity.builder()
                .name(request.getName())
                .document(request.getDocument())
                .birthDate(request.getBirthDate())
                .build());

        return ResponseEntity.ok(toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<MedicalResponse>> list() {
        List<MedicalResponse> responses = repository.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    private MedicalResponse toResponse(MedicalEntity entity) {
        return MedicalResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .document(entity.getDocument())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
