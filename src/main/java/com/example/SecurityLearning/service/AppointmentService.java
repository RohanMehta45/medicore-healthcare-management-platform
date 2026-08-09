package com.example.SecurityLearning.service;

import com.example.SecurityLearning.dto.AppointmentResponseDto;
import com.example.SecurityLearning.dto.CreateAppointmentRequestDto;
import com.example.SecurityLearning.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointmentsOfDoctor(Long userId);

    List<Appointment> getAllAppointmentsOfPatient(Long userId);

    AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto requestDto);

    Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId);
}