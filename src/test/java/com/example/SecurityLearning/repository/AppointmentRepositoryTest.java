package com.example.SecurityLearning.repository;

import com.example.SecurityLearning.entity.Appointment;
import com.example.SecurityLearning.entity.Doctor;
import com.example.SecurityLearning.entity.Patient;
import com.example.SecurityLearning.entity.User;
import com.example.SecurityLearning.entity.type.AuthProviderType;
import com.example.SecurityLearning.entity.type.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should find appointments by doctor")
    void shouldFindAppointmentsByDoctor() {

        // ---------- Arrange ----------

        User doctorUser = User.builder()
                .userName("doctor1")
                .password("password")
                .providerType(AuthProviderType.EMAIL)
                .roles(Set.of(RoleType.DOCTOR))
                .build();

        doctorUser = userRepository.save(doctorUser);

        Doctor doctor = Doctor.builder()
                .user(doctorUser)
                .name("Dr. John")
                .email("john@test.com")
                .specialization("Cardiology")
                .build();

        doctor = doctorRepository.save(doctor);

        User patientUser = User.builder()
                .userName("patient1")
                .password("password")
                .providerType(AuthProviderType.EMAIL)
                .roles(Set.of(RoleType.PATIENT))
                .build();

        patientUser = userRepository.save(patientUser);

        Patient patient = Patient.builder()
                .user(patientUser)
                .name("Alice")
                .email("alice@test.com")
                .build();

        patient = patientRepository.save(patient);

        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("Regular Checkup")
                .doctor(doctor)
                .patient(patient)
                .build();

        appointmentRepository.save(appointment);

        // ---------- Act ----------

        List<Appointment> appointments =
                appointmentRepository.findByDoctorId(doctor.getId());

        // ---------- Assert ----------

        assertEquals(1, appointments.size());
        assertEquals(doctor.getId(), appointments.getFirst().getDoctor().getId());
        assertEquals(patient.getId(), appointments.getFirst().getPatient().getId());
        assertEquals("Regular Checkup", appointments.getFirst().getReason());
    }
}