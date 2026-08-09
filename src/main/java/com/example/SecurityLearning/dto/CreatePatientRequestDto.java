package com.example.SecurityLearning.dto;

import com.example.SecurityLearning.entity.type.BloodGroupType;
import com.example.SecurityLearning.entity.type.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class CreatePatientRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Past
    private LocalDate birthDate;

    @NotNull
    private GenderType gender;

    @NotNull
    private BloodGroupType bloodGroup;
}
