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
@DiscriminatorValue("PSYCHOPEDAGOGIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Psychopedagogist extends User {

    @Column(nullable = false)
    private String professionalRegistration;

    @Column(nullable = false)
    private String academicFormation;

    @Column(nullable = true)
    private String specializations;

    @Column(nullable = false)
    private Integer experienceInYears;
}
