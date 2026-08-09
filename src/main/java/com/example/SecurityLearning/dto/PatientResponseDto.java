package com.example.SecurityLearning.dto;

import com.example.SecurityLearning.entity.type.BloodGroupType;
import com.example.SecurityLearning.entity.type.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        name = "Patient Response",
        description = "Patient details returned by the API."
)
public class PatientResponseDto {

    @Schema(
            description = "Full name of the patient",
            example = "Rohan Mehta"
    )
    private String name;

    @Schema(
            description = "Registered email address",
            example = "rohan@gmail.com"
    )
    private String email;

    @Schema(
            description = "Patient date of birth",
            example = "2000-05-15"
    )
    private LocalDate birthDate;

    @Schema(
            description = "Gender of the patient",
            example = "MALE"
    )
    private GenderType gender;

    @Schema(
            description = "Blood group of the patient",
            example = "O_POSITIVE"
    )
    private BloodGroupType bloodGroup;
}