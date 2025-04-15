package com.example.medical.repository;

import com.example.medical.entity.MedicalEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MedicalRepositoryTest {

    @Autowired
    private MedicalRepository repository;

    @Test
    void shouldSaveAndFindMedical() {
        MedicalEntity entity = MedicalEntity.builder()
                .name("Dr. Kenobi")
                .document("123456789")
                .birthDate(LocalDate.of(1975, 5, 4))
                .build();

        repository.save(entity);

        List<MedicalEntity> all = repository.findAll();
        assertFalse(all.isEmpty());
    }
}
