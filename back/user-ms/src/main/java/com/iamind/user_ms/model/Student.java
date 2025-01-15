package com.iamind.user_ms.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Student extends User {

    //    @Column(nullable = false)
    private String schoolYear;

    //    @Column(nullable = false)
    private String classRoom;

    //    @Column(nullable = false)
    private String school;

    //    @Column(nullable = false)
    private String shift;

    //    @Column(nullable = true, length = 1000)
    private String mentalHealthHistory;

    //    @Column(nullable = true, length = 1000)
    private String previousDiagnoses;

    //    @Column(nullable = true, length = 500)
    private String medicationsInUse;

    //    @Column(nullable = true, length = 500)
    private String allergies;

    //    @Column(nullable = false)
    private String guardianName;

    //    @Column(nullable = false)
    private String guardianPhone;

    //    @Column(nullable = false)
    private String guardianEmail;

    @Override
    public String generateHashKey() {
        return UUID.nameUUIDFromBytes(
                this.getFirstName()
                        .concat(this.getLastName())
                        .concat(this.getSchool())
                        .concat(this.getClassRoom())
                        .concat(this.getSchool())
                        .concat(this.getShift())
                        .getBytes()
        ).toString();
    }
}
