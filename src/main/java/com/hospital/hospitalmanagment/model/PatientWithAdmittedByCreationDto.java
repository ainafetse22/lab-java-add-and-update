package com.hospital.hospitalmanagment.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientWithAdmittedByCreationDto {
    @NotNull(message = "Patient ID is required")
    @Min(value = 100000, message = "Value must be a 6-digit integer")
    @Max(value = 999999, message = "Value must be a 6-digit integer")
    private int patientId;
    @NotNull(message = "Name is required")
    @Size(max = 255, message = "Patient name must not exceed 255 characters")
    private String name;
    @NotNull(message = "Patient birthday is required")
    @Past(message = "Patient birthday must be in the past or present")
    private Date birthday;
    @Digits(integer = 6, fraction = 0, message = "Employee ID must be a numeric value with up to 6 digits")
    private int employeeId;
}
