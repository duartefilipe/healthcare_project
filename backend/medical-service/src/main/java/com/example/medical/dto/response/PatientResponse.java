package com.example.medical.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatientResponse {
    private Long id;
    private String healthCardNumber;
    private Long doctorId;
    private Long userId;
}
