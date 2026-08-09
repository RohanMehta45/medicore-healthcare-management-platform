package com.example.SecurityLearning.dto;

import com.example.SecurityLearning.entity.type.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "Sign Up Request",
        description = "Request body for registering a new user."
)
public class SignUpRequestDto {

    @Schema(
            description = "Email address of the user",
            example = "rohan@gmail.com"
    )
    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email cannot be blank")
    private String username;

    @Schema(
            description = "Password (8-20 characters)",
            example = "Password@123"
    )
    @NotBlank(message = "Password cannot be blank")
    @Size(
            min = 8,
            max = 20,
            message = "Password must contain 8-20 characters"
    )
    private String password;

    @Schema(
            description = "Full name of the user",
            example = "Rohan Mehta"
    )
    @NotBlank(message = "Name cannot be blank")
    @Size(
            min = 3,
            max = 50,
            message = "Name must contain 3-50 characters"
    )
    private String name;

    @Schema(
            description = "Roles assigned to the user",
            example = "[\"PATIENT\"]"
    )
    @Builder.Default
    private Set<RoleType> roles = Set.of(RoleType.PATIENT);

}