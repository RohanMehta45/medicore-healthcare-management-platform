package com.example.SecurityLearning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Login Response",
        description = "Response returned after successful authentication."
)
public class LoginResponseDto {

    @Schema(
            description = "JWT access token used for authenticated requests",
            example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    private String jwt;

    @Schema(
            description = "Unique ID of the authenticated user",
            example = "1"
    )
    private Long userId;
    

}