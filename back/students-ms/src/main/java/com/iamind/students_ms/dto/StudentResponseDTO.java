package com.iamind.students_ms.dto;

import java.time.LocalDate;

public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String school,
        String registrationNumber,
        String email
) {
}
