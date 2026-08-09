package com.example.SecurityLearning.controller;

import com.example.SecurityLearning.entity.User;
import com.example.SecurityLearning.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
        name = "Patient APIs",
        description = "Operations related to patient management"
)
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final AppointmentService appointmentService;

    @Operation(
            summary = "Get Logged-in Patient Appointments",
            description = "Returns all appointments associated with the currently authenticated patient."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Appointments retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. JWT token is missing or invalid."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            )
    })
    @GetMapping("/appointments")
    public ResponseEntity<?> getMyAppointments() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        log.info("Fetching appointments for patientId={}", user.getId());

        ResponseEntity<?> response = ResponseEntity.ok(
                appointmentService.getAllAppointmentsOfPatient(user.getId())
        );

        log.info("Appointments retrieved successfully for patientId={}", user.getId());

        return response;
    }
}