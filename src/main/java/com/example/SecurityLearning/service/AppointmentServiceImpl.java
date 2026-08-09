package com.example.SecurityLearning.service;

import com.example.SecurityLearning.dto.AppointmentResponseDto;
import com.example.SecurityLearning.dto.CreateAppointmentRequestDto;
import com.example.SecurityLearning.entity.Appointment;
import com.example.SecurityLearning.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public List<Appointment> getAllAppointmentsOfDoctor(Long userId) {

        log.info("Fetching appointments for doctorId={}", userId);

        List<Appointment> appointments =
                appointmentRepository.findByDoctorUserId(userId);

        log.info("Retrieved {} appointments for doctorId={}",
                appointments.size(),
                userId);

        return appointments;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT')")
    public List<Appointment> getAllAppointmentsOfPatient(Long userId) {

        log.info("Fetching appointments for patientId={}", userId);

        List<Appointment> appointments =
                appointmentRepository.findByPatientUserId(userId);

        log.info("Retrieved {} appointments for patientId={}",
                appointments.size(),
                userId);

        return appointments;
    }

    @Override
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto requestDto) {

        log.info("Creating new appointment.");

        return null;
    }

    @Override
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {

        log.info("Reassigning appointmentId={} to doctorId={}",
                appointmentId,
                doctorId);

        return null;
    }
}