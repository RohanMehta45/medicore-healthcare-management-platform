package com.example.SecurityLearning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Create Appointment Request",
        description = "Request body for creating a new appointment."
)
public class CreateAppointmentRequestDto {

    @Schema(
            description = "Unique ID of the patient",
            example = "1"
    )
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @Schema(
            description = "Unique ID of the doctor",
            example = "5"
    )
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @Schema(
            description = "Appointment date and time",
            example = "2026-07-30T10:30:00"
    )
    @NotNull(message = "Appointment time is required")
    @Future(message = "Appointment should be in the future")
    private LocalDateTime appointmentTime;

    @Schema(
            description = "Reason for booking the appointment",
            example = "Routine health check-up"
    )
    @NotBlank(message = "Reason is required")
    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    private String reason;

}