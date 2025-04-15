package com.example.medical.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.medical.client.UserClient;
import com.example.medical.dto.request.PatientRequest;
import com.example.medical.entity.PatientEntity;
import com.example.medical.repository.PatientRepository;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;
    private final UserClient userClient;
    private final RestTemplate restTemplate;

    @Transactional
    public void createPatient(Long userId, PatientRequest request) {
        PatientEntity patient = PatientEntity.builder()
                .id(userId)
                .doctorId(request.getDoctorId())
                .name(request.getName())
                .document(request.getDocument())
                .birthDate(request.getBirthDate())
                .healthCardNumber(request.getHealthCardNumber())
                .build();

        repository.save(patient);
    }

    public List<PatientEntity> getAll() {
        return repository.findAll(); 
    }
    
    
    

    
}
