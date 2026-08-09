package com.example.SecurityLearning.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Method Security  APIs",
        description = "APIs for Method Security"
)
@RestController
@RequestMapping("/method")
public class MethodSecurityController {

    // =====================================
    // Only ADMIN
    // =====================================
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {

        return "Welcome ADMIN";
    }

    // =====================================
    // ADMIN or DOCTOR
    // =====================================
    @GetMapping("/doctor")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public String doctor() {

        return "Doctor Resource";
    }

    // =====================================
    // Permission Based
    // =====================================
    @GetMapping("/patient-read")
    @PreAuthorize("hasAuthority('patient:read')")
    public String patientRead() {

        return "Patient Read Permission Granted";
    }

    // =====================================
    // Permission Based
    // =====================================
    @PostMapping("/patient-write")
    @PreAuthorize("hasAuthority('patient:write')")
    public String patientWrite() {

        return "Patient Write Permission Granted";
    }

    // =====================================
    // Old Annotation
    // =====================================
    @GetMapping("/secured")
    @Secured("ROLE_ADMIN")
    public String secured() {

        return "@Secured Working";
    }

    // =====================================
    // Show Logged In User
    // =====================================
    @GetMapping("/me")
    public Object currentUser(Authentication authentication) {

        return authentication;
    }

    // =====================================
    // Post Authorize Example
    // =====================================
    @GetMapping("/post")
    @PostAuthorize("returnObject.equals(authentication.name)")
    public String postAuthorize(Authentication authentication) {

        return authentication.getName();
    }

}
