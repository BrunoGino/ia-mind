package com.iamind.user_ms.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("STUDENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends User {

    @Column(nullable = false)
    private String schoolYear;

    @Column(nullable = false)
    private String classRoom;

    @Column(nullable = false)
    private String school;

    @Column(nullable = false)
    private String shift;

    @Column(nullable = true, length = 1000)
    private String mentalHealthHistory;

    @Column(nullable = true, length = 1000)
    private String previousDiagnoses;

    @Column(nullable = true, length = 500)
    private String medicationsInUse;

    @Column(nullable = true, length = 500)
    private String allergies;

    @Column(nullable = false)
    private String guardianName;

    @Column(nullable = false)
    private String guardianPhone;

    @Column(nullable = false)
    private String guardianEmail;
}
