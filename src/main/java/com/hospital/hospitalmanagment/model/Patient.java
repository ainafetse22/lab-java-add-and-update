package com.hospital.hospitalmanagment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="patient")
public class Patient {
    @Id
    //@NotEmpty(message = "You must provide and ID")
    //@Digits(integer = 6, fraction = 0)
    private int patientId;

    //@Size(min=3, max=255)
    //@Pattern(regexp = "^[a-zA-Z'-]+$", message = "Name must contain only letters")
    //@NotEmpty(message = "You must provide a name")
    private String name;

    //@Past
    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "admitted_by")
    private Employee admittedBy;

    public Patient(int patientId, String name, Date birthday) {
        this.patientId = patientId;
        this.name = name;
        this.birthday = birthday;
    }

    public Patient(int patientId, String name, Date birthday, Employee admittedBy) {
        this.patientId = patientId;
        this.name = name;
        this.birthday = birthday;
        this.admittedBy = admittedBy;
    }
}
