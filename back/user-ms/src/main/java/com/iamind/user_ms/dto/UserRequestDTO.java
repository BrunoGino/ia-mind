package com.iamind.user_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRequestDTO(
        @NotBlank(message = "First name is mandatory")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        String lastName,

        @NotNull(message = "Date of birth is mandatory")
        LocalDate dateOfBirth,

        @NotBlank(message = "School name is mandatory")
        String school,

        @NotBlank(message = "Registration Number (RA) is mandatory")
        String registrationNumber,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        String email
) {
}
