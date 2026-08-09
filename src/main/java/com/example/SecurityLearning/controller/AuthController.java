package com.example.SecurityLearning.controller;

import com.example.SecurityLearning.dto.LoginRequestDto;
import com.example.SecurityLearning.dto.LoginResponseDto;
import com.example.SecurityLearning.dto.SignUpRequestDto;
import com.example.SecurityLearning.dto.SignUpResponseDto;
import com.example.SecurityLearning.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
        name = "Authentication APIs",
        description = "APIs for user registration and authentication"
)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register a New User",
            description = "Creates a new user account using email and password."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data or validation failed"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists"
            )
    })
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(
            @RequestBody @Valid SignUpRequestDto signUpRequestDto) {

        log.info("Signup request received for email={}",
                signUpRequestDto.getUsername());

        SignUpResponseDto response =
                authService.signup(signUpRequestDto);

        log.info("User registered successfully with userId={}",
                response.getId());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "User Login",
            description = "Authenticates the user and returns a JWT access token."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto loginRequestDto) {

        log.info("Login request received for email={}",
                loginRequestDto.getUserName());

        LoginResponseDto response =
                authService.login(loginRequestDto);

        log.info("User logged in successfully. userId={}",
                response.getUserId());

        return ResponseEntity.ok(response);
    }
}