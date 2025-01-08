package com.iamind.user_ms.dto;

import java.time.LocalDate;

public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String gender,
        String fullAddress,
        String email,
        String phone,
        String schoolYear,
        String classRoom,
        String school,
        String shift,
        String mentalHealthHistory,
        String previousDiagnoses,
        String medicationsInUse,
        String allergies,
        String guardianName,
        String guardianPhone,
        String guardianEmail,
        String notes
) {
}
