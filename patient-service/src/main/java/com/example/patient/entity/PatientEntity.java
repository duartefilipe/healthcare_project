package com.example.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String name;

    @NotBlank(message = "Documento obrigatório")
    @Size(min = 9, max = 15)
    private String document;

    @NotNull(message = "Data de nascimento obrigatória")
    private LocalDate birthDate;

}
