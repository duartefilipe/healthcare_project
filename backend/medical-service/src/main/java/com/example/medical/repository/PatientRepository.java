package com.example.medical.repository;

import com.example.medical.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findByDoctorId(Long doctorId);
}
