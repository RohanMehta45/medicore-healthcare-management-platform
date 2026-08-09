package com.example.SecurityLearning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Appointment Response",
        description = "Represents the details of an appointment returned by the API."
)
public class AppointmentResponseDto {

    @Schema(
            description = "Unique appointment ID",
            example = "101"
    )
    private Long id;

    @Schema(
            description = "Full name of the patient",
            example = "Rohan Mehta"
    )
    private String patientName;

    @Schema(
            description = "Full name of the doctor",
            example = "Dr. Amit Sharma"
    )
    private String doctorName;

    @Schema(
            description = "Scheduled appointment date and time",
            example = "2026-07-25T10:30:00"
    )
    private LocalDateTime appointmentTime;

    @Schema(
            description = "Reason for the appointment",
            example = "Routine health check-up"
    )
    private String reason;

}