package com.example.medical.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "document")
    private String document;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "health_card_number")
    private String healthCardNumber;

    @Column(name = "doctor_id")
    private Long doctorId;
}
