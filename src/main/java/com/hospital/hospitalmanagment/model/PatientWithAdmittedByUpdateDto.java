package com.hospital.hospitalmanagment.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientWithAdmittedByUpdateDto {
    @NotNull(message = "Patient ID is required")
    @Min(value = 100000, message = "Value must be a 6-digit integer")
    @Max(value = 999999, message = "Value must be a 6-digit integer")
    private int patientId;
    @Size(max = 255, message = "Patient name must not exceed 255 characters")
    private String name;
    @Past(message = "Patient birthday must be in the past or present")
    private Date birthday;
    @Min(value = 100000, message = "Value must be a 6-digit integer")
    @Max(value = 999999, message = "Value must be a 6-digit integer")
    private int employeeId;
}
