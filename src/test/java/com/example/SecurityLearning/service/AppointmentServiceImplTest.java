package com.example.SecurityLearning.service;

import com.example.SecurityLearning.entity.Appointment;
import com.example.SecurityLearning.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    void shouldReturnAllAppointmentsOfDoctor() {

        // Arrange
        Long doctorUserId = 1L;

        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();

        List<Appointment> expectedAppointments =
                List.of(appointment1, appointment2);

        when(appointmentRepository.findByDoctorUserId(doctorUserId))
                .thenReturn(expectedAppointments);

        // Act
        List<Appointment> actualAppointments =
                appointmentService.getAllAppointmentsOfDoctor(doctorUserId);

        // Assert
        assertEquals(expectedAppointments, actualAppointments);

        verify(appointmentRepository, times(1))
                .findByDoctorUserId(doctorUserId);
    }
}