package com.iamind.user_ms.dto;

import java.time.LocalDate;

public record PsychopedagogistResponseDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String gender,
        String fullAddress,
        String email,
        String phone,
        String professionalRegistration,
        String academicFormation,
        String specializations,
        Integer experienceInYears,
        String notes
) {
}
