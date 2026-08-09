package com.example.SecurityLearning.controller;

import com.example.SecurityLearning.entity.Appointment;
import com.example.SecurityLearning.service.AppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AppointmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AppointmentService appointmentService;

    @Test
    @DisplayName("Should return doctor's appointments")
    void shouldReturnAppointmentsOfDoctor() throws Exception {

        Long doctorId = 1L;

        Appointment a1 = new Appointment();
        a1.setId(101L);

        Appointment a2 = new Appointment();
        a2.setId(102L);

        when(appointmentService.getAllAppointmentsOfDoctor(doctorId))
                .thenReturn(List.of(a1, a2));

        mockMvc.perform(get("/appointments/doctor/{id}", doctorId))
                .andExpect(status().isOk());

        verify(appointmentService, times(1))
                .getAllAppointmentsOfDoctor(doctorId);
    }
}