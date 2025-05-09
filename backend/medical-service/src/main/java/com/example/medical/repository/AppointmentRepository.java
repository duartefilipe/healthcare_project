package com.example.medical.repository;

import com.example.medical.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    
    List<AppointmentEntity> findByPatientId(Long patientId);
}
