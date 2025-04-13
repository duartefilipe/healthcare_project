package com.example.patient.repository;

import com.example.patient.entity.PatientEntity; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {}
