package com.example.medical.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class PatientRequest {
    private String name;
    private String document;
    private LocalDate birthDate;
    private String healthCardNumber;
    private Long doctorId;
}
