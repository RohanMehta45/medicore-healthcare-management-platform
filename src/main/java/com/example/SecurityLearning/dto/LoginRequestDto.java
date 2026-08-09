package com.example.SecurityLearning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Login Request",
        description = "Request body for user authentication."
)
public class LoginRequestDto {

    @Schema(
            description = "Registered email address of the user",
            example = "rohan@gmail.com"
    )
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String userName;

    @Schema(
            description = "User account password",
            example = "Password@123"
    )
    @NotBlank(message = "Password is required")
    private String password;
}