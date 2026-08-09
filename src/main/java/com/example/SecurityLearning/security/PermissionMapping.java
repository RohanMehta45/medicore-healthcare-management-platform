package com.example.SecurityLearning.security;

import com.example.SecurityLearning.entity.type.PermissionType;
import com.example.SecurityLearning.entity.type.RoleType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.SecurityLearning.entity.type.PermissionType.*;
import static com.example.SecurityLearning.entity.type.RoleType.*;

public class PermissionMapping {

    private static final Map<RoleType, Set<PermissionType>> ROLE_PERMISSION_MAP = Map.of(

            PATIENT,
            Set.of(
                    PATIENT_READ,
                    APPOINTMENT_READ,
                    APPOINTMENT_WRITE
            ),

            DOCTOR,
            Set.of(
                    PATIENT_READ,
                    APPOINTMENT_READ,
                    APPOINTMENT_WRITE,
                    APPOINTMENT_DELETE,
                    DOCTOR_READ
            ),

            ADMIN,
            Set.of(
                    PATIENT_READ,
                    PATIENT_WRITE,

                    DOCTOR_READ,
                    DOCTOR_WRITE,

                    APPOINTMENT_READ,
                    APPOINTMENT_WRITE,
                    APPOINTMENT_DELETE,

                    USER_MANAGE,
                    REPORT_VIEW
            )
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(RoleType role) {

        return ROLE_PERMISSION_MAP
                .get(role)
                .stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
