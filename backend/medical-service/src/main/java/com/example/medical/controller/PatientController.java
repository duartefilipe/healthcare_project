package com.example.medical.controller;

import com.example.medical.dto.request.PatientRequest;
import com.example.medical.entity.PatientEntity;
import com.example.medical.service.PatientService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> create(@PathVariable Long userId, @RequestBody PatientRequest request) {
        patientService.createPatient(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/patients")
public ResponseEntity<List<PatientEntity>> getAllPatients(@RequestHeader("Authorization") String authHeader) {
    return ResponseEntity.ok(patientService.getAll());
}


}
