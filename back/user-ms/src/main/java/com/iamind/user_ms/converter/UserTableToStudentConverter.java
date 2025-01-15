package com.iamind.user_ms.converter;

import com.iamind.user_ms.model.Student;
import com.iamind.user_ms.model.dynamodb.UserTable;
import org.springframework.stereotype.Component;

@Component
public class UserTableToStudentConverter {
    public Student convert(UserTable userTable) {
        if (userTable == null) {
            throw new IllegalArgumentException("UserTable cannot be null");
        }

        return Student.builder()
                .firstName(userTable.getFirstName())
                .lastName(userTable.getLastName())
                .dateOfBirth(userTable.getDateOfBirth())
                .gender(userTable.getGender())
                .fullAddress(userTable.getFullAddress())
                .email(userTable.getEmail())
                .phone(userTable.getPhone())
                .notes(userTable.getNotes())
                .schoolYear(userTable.getSchoolYear())
                .classRoom(userTable.getClassRoom())
                .school(userTable.getSchool())
                .shift(userTable.getShift())
                .mentalHealthHistory(userTable.getMentalHealthHistory())
                .previousDiagnoses(userTable.getPreviousDiagnoses())
                .medicationsInUse(userTable.getMedicationsInUse())
                .allergies(userTable.getAllergies())
                .guardianName(userTable.getGuardianName())
                .guardianPhone(userTable.getGuardianPhone())
                .guardianEmail(userTable.getGuardianEmail())
                .build();
    }

    public UserTable convert(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        UserTable userTable = new UserTable();
        userTable.setId(student.getId());
        userTable.setFirstName(student.getFirstName());
        userTable.setLastName(student.getLastName());
        userTable.setDateOfBirth(student.getDateOfBirth());
        userTable.setGender(student.getGender());
        userTable.setFullAddress(student.getFullAddress());
        userTable.setEmail(student.getEmail());
        userTable.setPhone(student.getPhone());
        userTable.setNotes(student.getNotes());
        userTable.setSchoolYear(student.getSchoolYear());
        userTable.setClassRoom(student.getClassRoom());
        userTable.setSchool(student.getSchool());
        userTable.setShift(student.getShift());
        userTable.setMentalHealthHistory(student.getMentalHealthHistory());
        userTable.setPreviousDiagnoses(student.getPreviousDiagnoses());
        userTable.setMedicationsInUse(student.getMedicationsInUse());
        userTable.setAllergies(student.getAllergies());
        userTable.setGuardianName(student.getGuardianName());
        userTable.setGuardianPhone(student.getGuardianPhone());
        userTable.setGuardianEmail(student.getGuardianEmail());

        return userTable;
    }

}
