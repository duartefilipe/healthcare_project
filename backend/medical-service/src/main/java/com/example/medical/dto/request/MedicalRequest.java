package com.example.medical.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalRequest {
    @NotBlank(message = "Nome obrigatório")
    private String name;

    @NotBlank(message = "Documento obrigatório")
    @Size(min = 9, max = 15)
    private String document;

    @NotNull(message = "Data de nascimento obrigatória")
    private LocalDate birthDate;
}
