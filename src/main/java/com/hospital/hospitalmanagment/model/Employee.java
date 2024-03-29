package com.hospital.hospitalmanagment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name= "employee")
public class Employee {
    @Id
    private int employeeId;

    private String department;

    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;

}
