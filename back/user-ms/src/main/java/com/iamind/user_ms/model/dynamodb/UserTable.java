package com.iamind.user_ms.model.dynamodb;

import com.iamind.user_ms.converter.LocalDateConverter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@DynamoDbBean
public class UserTable {

    private String id;

    private String userType;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String fullAddress;

    private String email;

    private String phone;

    private String notes;

    private String professionalRegistration;

    private String academicFormation;

    private String specializations;

    private String experienceInYears;

    private String schoolYear;

    private String classRoom;

    private String school;

    private String shift;

    private String mentalHealthHistory;

    private String previousDiagnoses;

    private String medicationsInUse;

    private String allergies;

    private String guardianName;

    private String guardianPhone;

    private String guardianEmail;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDbConvertedBy(LocalDateConverter.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProfessionalRegistration() {
        return professionalRegistration;
    }

    public void setProfessionalRegistration(String professionalRegistration) {
        this.professionalRegistration = professionalRegistration;
    }

    public String getAcademicFormation() {
        return academicFormation;
    }

    public void setAcademicFormation(String academicFormation) {
        this.academicFormation = academicFormation;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public String getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(String experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getMentalHealthHistory() {
        return mentalHealthHistory;
    }

    public void setMentalHealthHistory(String mentalHealthHistory) {
        this.mentalHealthHistory = mentalHealthHistory;
    }

    public String getPreviousDiagnoses() {
        return previousDiagnoses;
    }

    public void setPreviousDiagnoses(String previousDiagnoses) {
        this.previousDiagnoses = previousDiagnoses;
    }

    public String getMedicationsInUse() {
        return medicationsInUse;
    }

    public void setMedicationsInUse(String medicationsInUse) {
        this.medicationsInUse = medicationsInUse;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getGuardianEmail() {
        return guardianEmail;
    }

    public void setGuardianEmail(String guardianEmail) {
        this.guardianEmail = guardianEmail;
    }
}
