package com.iamind.user_ms.dto;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String school,
        String registrationNumber,
        String email
) {
}
