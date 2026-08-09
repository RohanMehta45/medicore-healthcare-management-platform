package com.example.SecurityLearning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Sign Up Response",
        description = "Response returned after successful user registration."
)
public class SignUpResponseDto {

    @Schema(
            description = "Unique ID of the registered user",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Registered email address",
            example = "rohan@gmail.com"
    )
    private String username;
}