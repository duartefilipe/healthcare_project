package com.example.medical.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    @NotNull
    private Long patientId;

    @NotNull
    @Future
    private LocalDateTime date;
}
