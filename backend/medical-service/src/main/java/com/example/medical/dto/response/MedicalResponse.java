package com.example.medical.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MedicalResponse {
    private Long id;
    private String name;
    private String document;
    private LocalDate birthDate;
}
