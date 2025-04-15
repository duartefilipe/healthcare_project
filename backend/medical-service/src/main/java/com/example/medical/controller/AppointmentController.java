package com.example.medical.controller;

import com.example.medical.dto.request.AppointmentRequest;
import com.example.medical.dto.response.AppointmentResponse;
import com.example.medical.entity.AppointmentEntity;
import com.example.medical.entity.PatientEntity;
import com.example.medical.repository.AppointmentRepository;
import com.example.medical.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody AppointmentRequest request) {
        PatientEntity patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        AppointmentEntity saved = appointmentRepository.save(
                AppointmentEntity.builder()
                        .date(request.getDate())
                        .patient(patient)
                        .build()
        );

        return ResponseEntity.ok(toResponse(saved));
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> listByPatient(@PathVariable Long patientId) {
        List<AppointmentEntity> appointments = appointmentRepository.findByPatientId(patientId);
        return ResponseEntity.ok(appointments.stream().map(this::toResponse).toList());
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResponse>> getAll() {
        List<AppointmentEntity> all = appointmentRepository.findAll();
        return ResponseEntity.ok(all.stream().map(this::toResponse).toList());
    }

    private AppointmentResponse toResponse(AppointmentEntity entity) {
        return AppointmentResponse.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .patientId(entity.getPatient().getId())
                .doctorId(entity.getPatient().getDoctorId())
                .build();
    }

    private String extractUsername(String authHeader) {
        String base64 = authHeader.substring("Basic ".length());
        String decoded = new String(Base64.getDecoder().decode(base64));
        return decoded.split(":")[0];

    }
}
