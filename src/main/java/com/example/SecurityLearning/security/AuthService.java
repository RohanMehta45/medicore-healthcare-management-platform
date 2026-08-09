package com.example.SecurityLearning.security;

import com.example.SecurityLearning.dto.LoginRequestDto;
import com.example.SecurityLearning.dto.LoginResponseDto;
import com.example.SecurityLearning.dto.SignUpRequestDto;
import com.example.SecurityLearning.dto.SignUpResponseDto;
import com.example.SecurityLearning.entity.Patient;
import com.example.SecurityLearning.entity.User;
import com.example.SecurityLearning.entity.type.AuthProviderType;
import com.example.SecurityLearning.repository.PatientRepository;
import com.example.SecurityLearning.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    // ======================================================
    // LOGIN
    // ======================================================

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        log.info("Authenticating user with email={}",loginRequestDto.getUserName());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUserName(),
                        loginRequestDto.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        log.info("Authentication successful for userId={}",
                user.getId());

        String token = authUtil.generateAccessToken(user);

        log.info("JWT access token generated for userId={}",
                user.getId());


        return new LoginResponseDto(token, user.getId());
    }

    // ======================================================
    // INTERNAL SIGNUP
    // Used by Email, Google and GitHub
    // ======================================================


    public User signUpInternal(

            SignUpRequestDto signupRequestDto,
            AuthProviderType providerType,
            String providerId
    ) {

        log.info("Starting {} signup for email={}",
                providerType,
                signupRequestDto.getUsername());


        User user = userRepository
                .findByUserName(signupRequestDto.getUsername())
                .orElse(null);

        if (user != null) {

            log.warn("Signup failed. User already exists with email={}",
                    signupRequestDto.getUsername());

            throw new IllegalArgumentException("User already exists");
        }

        user = User.builder()
                .userName(signupRequestDto.getUsername())
                .providerType(providerType)
                .providerId(providerId)
                .roles(signupRequestDto.getRoles())
                .build();

        // Password only for EMAIL signup
        if (providerType == AuthProviderType.EMAIL) {
            user.setPassword(
                    passwordEncoder.encode(signupRequestDto.getPassword())
            );
        }

        user = userRepository.save(user);

        log.info("User account created successfully. userId={}",
                user.getId());

        Patient patient = Patient.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getUsername())
                .user(user)
                .build();

        patientRepository.save(patient);

        log.info("Patient profile created successfully for userId={}",
                user.getId());

        return user;
    }

    // ======================================================
    // EMAIL SIGNUP
    // ======================================================

    public SignUpResponseDto signup(SignUpRequestDto signupRequestDto) {

        log.info("Processing email signup.");

        User user = signUpInternal(
                signupRequestDto,
                AuthProviderType.EMAIL,
                null
        );

        log.info("Email signup completed successfully. userId={}",
                user.getId());

        return new SignUpResponseDto(
                user.getId(),
                user.getUsername()
        );

    }


    // ======================================================
    // GOOGLE / GITHUB LOGIN
    // ======================================================

    @Transactional
    public LoginResponseDto handleOAuth2LoginRequest(
            OAuth2User oAuth2User,
            String registrationId
    ) {

        log.info("Processing OAuth2 login using provider={}",
                registrationId);

        AuthProviderType providerType =
                authUtil.getProviderTypeFromRegistrationId(registrationId);

        String providerId =
                authUtil.determineProviderIdFromOAuth2User(
                        oAuth2User,
                        registrationId
                );

        User user = userRepository
                .findByProviderIdAndProviderType(
                        providerId,
                        providerType
                )
                .orElse(null);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User emailUser = null;

        if (email != null) {
            emailUser = userRepository.findByUserName(email).orElse(null);
        }

        // -----------------------------
        // New OAuth User
        // -----------------------------

        if (user == null && emailUser == null) {

            log.info("New OAuth user detected. Creating account.");

            String username =
                    authUtil.determineUsernameFromOAuth2User(
                            oAuth2User,
                            registrationId,
                            providerId
                    );

            SignUpRequestDto dto = SignUpRequestDto.builder()
                    .username(username)
                    .password(null)
                    .name(name)
                    .build();

            user = signUpInternal(
                    dto,
                    providerType,
                    providerId
            );

        }

        // -----------------------------
        // Existing OAuth User
        // -----------------------------

        else if (user != null) {

            log.info("Existing OAuth user logged in. userId={}",
                    user.getId());

            if (email != null
                    && !email.isBlank()
                    && !email.equals(user.getUsername())) {

                user.setUserName(email);

                userRepository.save(user);
            }

        }

        // -----------------------------
        // Email already registered
        // -----------------------------

        else {

            log.warn(
                    "OAuth login rejected. Email already registered with provider={}",
                    emailUser.getProviderType()
            );

            throw new IllegalArgumentException(
                    "This email is already registered using "
                            + emailUser.getProviderType()
            );

        }

        String token = authUtil.generateAccessToken(user);

        log.debug("JWT generated for OAuth userId={}",
                user.getId());   

        return new LoginResponseDto(
                token,
                user.getId()
        );
    }
}