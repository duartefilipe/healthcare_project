package com.example.medical.repository;

import com.example.medical.entity.MedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRepository extends JpaRepository<MedicalEntity, Long> {
}
