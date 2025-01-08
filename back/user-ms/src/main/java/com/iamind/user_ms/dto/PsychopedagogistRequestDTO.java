package com.iamind.user_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PsychopedagogistRequestDTO(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last name is mandatory") String lastName,
        @NotNull(message = "Date of birth is mandatory") LocalDate dateOfBirth,
        @NotBlank(message = "Gender is mandatory") String gender,
        @NotBlank(message = "Full address is mandatory") String fullAddress,
        @NotBlank(message = "Email is mandatory") @Email String email,
        @NotBlank(message = "Phone is mandatory") String phone,
        @NotBlank(message = "Professional registration is mandatory") String professionalRegistration,
        @NotBlank(message = "Academic formation is mandatory") String academicFormation,
        String specializations,
        @NotNull(message = "Experience in years is mandatory") Integer experienceInYears
) {
}
