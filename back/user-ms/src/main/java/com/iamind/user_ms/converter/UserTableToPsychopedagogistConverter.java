package com.iamind.user_ms.converter;

import com.iamind.user_ms.model.Psychopedagogist;
import com.iamind.user_ms.model.dynamodb.UserTable;
import org.springframework.stereotype.Component;

@Component
public class UserTableToPsychopedagogistConverter {

    public Psychopedagogist convert(UserTable userTable) {
        if (userTable == null) {
            throw new IllegalArgumentException("UserTable cannot be null");
        }

        // Convert UserTable to Psychopedagogist
        return Psychopedagogist.builder()
                .id(userTable.getId())
                .firstName(userTable.getFirstName())
                .lastName(userTable.getLastName())
                .dateOfBirth(userTable.getDateOfBirth())
                .gender(userTable.getGender())
                .fullAddress(userTable.getFullAddress())
                .email(userTable.getEmail())
                .phone(userTable.getPhone())
                .notes(userTable.getNotes())
                .professionalRegistration(userTable.getProfessionalRegistration())
                .academicFormation(userTable.getAcademicFormation())
                .specializations(userTable.getSpecializations())
                .experienceInYears(parseExperienceInYears(userTable.getExperienceInYears()))
                .build();
    }

    // Convert Psychopedagogist to UserTable
    public UserTable convert(Psychopedagogist psychopedagogist) {
        if (psychopedagogist == null) {
            throw new IllegalArgumentException("Psychopedagogist cannot be null");
        }

        UserTable userTable = new UserTable();
        userTable.setId(psychopedagogist.getId());
        userTable.setFirstName(psychopedagogist.getFirstName());
        userTable.setLastName(psychopedagogist.getLastName());
        userTable.setDateOfBirth(psychopedagogist.getDateOfBirth());
        userTable.setGender(psychopedagogist.getGender());
        userTable.setFullAddress(psychopedagogist.getFullAddress());
        userTable.setEmail(psychopedagogist.getEmail());
        userTable.setPhone(psychopedagogist.getPhone());
        userTable.setNotes(psychopedagogist.getNotes());
        userTable.setProfessionalRegistration(psychopedagogist.getProfessionalRegistration());
        userTable.setAcademicFormation(psychopedagogist.getAcademicFormation());
        userTable.setSpecializations(psychopedagogist.getSpecializations());
        userTable.setExperienceInYears(psychopedagogist.getExperienceInYears() != null
                ? String.valueOf(psychopedagogist.getExperienceInYears())
                : null);

        return userTable;
    }

    private static Integer parseExperienceInYears(String experienceInYears) {
        try {
            return experienceInYears != null ? Integer.parseInt(experienceInYears) : null;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid format for experienceInYears: " + experienceInYears, e);
        }
    }
}
