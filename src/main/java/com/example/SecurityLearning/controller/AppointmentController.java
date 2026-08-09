package com.example.SecurityLearning.controller;

import com.example.SecurityLearning.dto.AppointmentResponseDto;
import com.example.SecurityLearning.dto.CreateAppointmentRequestDto;
import com.example.SecurityLearning.entity.Appointment;
import com.example.SecurityLearning.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Appointment APIs",
        description = "Appointment management"
)
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(
            @RequestBody CreateAppointmentRequestDto requestDto) {

        return ResponseEntity.ok(
                appointmentService.createNewAppointment(requestDto)
        );
    }

    @PutMapping("/{appointmentId}/doctor/{doctorId}")
    public ResponseEntity<Appointment> changeDoctor(

            @PathVariable Long appointmentId,

            @PathVariable Long doctorId) {

        return ResponseEntity.ok(

                appointmentService
                        .reAssignAppointmentToAnotherDoctor(
                                appointmentId,
                                doctorId
                        )

        );
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>>
    getAppointments(

            @PathVariable Long doctorId) {

        return ResponseEntity.ok(

                appointmentService
                        .getAllAppointmentsOfDoctor(doctorId)

        );
    }

}
