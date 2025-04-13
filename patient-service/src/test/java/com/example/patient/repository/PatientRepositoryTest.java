package com.example.patient.repository;

import com.example.patient.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository repository;

    @Test
    void shouldSaveAndRetrievePatient() {
        PatientEntity patient = PatientEntity.builder()
                .name("Han Solo")
                .document("1122334455")
                .birthDate(LocalDate.of(1977, 3, 15))
                .build();

        repository.save(patient);
        List<PatientEntity> result = repository.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Han Solo");
    }
}
