package com.example.SecurityLearning.controller;

import com.example.SecurityLearning.dto.PatientResponseDto;
import com.example.SecurityLearning.entity.Doctor;
import com.example.SecurityLearning.entity.Patient;
import com.example.SecurityLearning.repository.DoctorRepository;
import com.example.SecurityLearning.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


//    @GetMapping("/patients")
//    public ResponseEntity<List<Patient>> getAllPatients() {
//
//        return ResponseEntity.ok(patientRepository.findAll());
//    }

    @GetMapping("/patients")
    public ResponseEntity<?> getAllPatients() {

        return ResponseEntity.ok(
                patientRepository.findAll()
                        .stream()
                        .map(Patient::getName)
                        .toList()
        );

    }


    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {

        return ResponseEntity.ok(doctorRepository.findAll());
    }

}
