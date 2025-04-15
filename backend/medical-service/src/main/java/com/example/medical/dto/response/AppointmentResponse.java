package com.example.medical.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentResponse {
    private Long id;
    private LocalDateTime date;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
}
