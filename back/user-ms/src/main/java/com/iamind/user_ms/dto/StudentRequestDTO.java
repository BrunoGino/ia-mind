package com.iamind.user_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentRequestDTO(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last name is mandatory") String lastName,
        @NotNull(message = "Date of birth is mandatory") LocalDate dateOfBirth,
        @NotBlank(message = "Gender is mandatory") String gender,
        @NotBlank(message = "Full address is mandatory") String fullAddress,
        @NotBlank(message = "Email is mandatory") @Email String email,
        @NotBlank(message = "Phone is mandatory") String phone,
        @NotBlank(message = "School year is mandatory") String schoolYear,
        @NotBlank(message = "Classroom is mandatory") String classRoom,
        @NotBlank(message = "School is mandatory") String school,
        @NotBlank(message = "Shift is mandatory") String shift,
        String mentalHealthHistory,
        String previousDiagnoses,
        String medicationsInUse,
        String allergies,
        @NotBlank(message = "Guardian name is mandatory") String guardianName,
        @NotBlank(message = "Guardian phone is mandatory") String guardianPhone,
        @NotBlank(message = "Guardian email is mandatory") @Email String guardianEmail
) {
}
