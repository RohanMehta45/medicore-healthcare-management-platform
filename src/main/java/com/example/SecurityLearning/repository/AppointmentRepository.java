package com.example.SecurityLearning.repository;

import com.example.SecurityLearning.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorUserId(Long userId);

    List<Appointment> findByPatientUserId(Long userId);

    List<Appointment> findByDoctorId(Long id);
}