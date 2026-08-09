package com.example.SecurityLearning.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {

    // Patient Permissions
    PATIENT_READ("patient:read"),
    PATIENT_WRITE("patient:write"),

    // Doctor Permissions
    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),

    // Appointment Permissions
    APPOINTMENT_READ("appointment:read"),
    APPOINTMENT_WRITE("appointment:write"),
    APPOINTMENT_DELETE("appointment:delete"),

    // Admin Permissions
    USER_MANAGE("user:manage"),
    REPORT_VIEW("report:view");

    private final String permission;
}
