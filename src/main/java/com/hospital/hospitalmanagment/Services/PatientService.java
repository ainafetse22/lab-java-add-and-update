package com.hospital.hospitalmanagment.Services;

import com.hospital.hospitalmanagment.model.*;
import com.hospital.hospitalmanagment.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private EmployeeService employeeService;
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(int id) {
        return  patientRepository.findById(id);
    }

    public List<Patient> getPatientByBirthRange(Date start, Date finish) {
        return  patientRepository.findByBirthdayBetween(start, finish);
    }

    public List<Patient> getPatientByDoctorsDepartment(String department) {
        return patientRepository.findByDoctorsDepartment(department);
    }

    public List<Patient> getPatientByDoctorsStatus(Status status) {
        return patientRepository.findByDoctorsStatus(status);

    }

    public boolean addPatient(PatientWithAdmittedByCreationDto patient_dto) {
        int employeeId = patient_dto.getEmployeeId();
        Optional<Employee> admittingEmployee = employeeService.getEmployeeById(employeeId);
        if (admittingEmployee.isPresent()) {
            Patient patient=new Patient(patient_dto.getPatientId(),patient_dto.getName(),patient_dto.getBirthday());
            patient.setAdmittedBy(admittingEmployee.get());
            patientRepository.save(patient);
            return true;
        }
        return false;
    }


    public void updatePatientInfo(PatientWithAdmittedByUpdateDto patient) {
        int patientId = patient.getPatientId();
        Optional<Patient> db_patient = patientRepository.findById(patientId);
        if (db_patient.isPresent()){
            if(patient.getName()!=null){
                db_patient.get().setName(patient.getName());
            }
            if(patient.getBirthday()!= null){
                db_patient.get().setBirthday(patient.getBirthday());
            }
            if(patient.getEmployeeId()!=0){
                int employeeId = patient.getEmployeeId();
                Optional<Employee> admittingEmployee = employeeService.getEmployeeById(employeeId);
                admittingEmployee.ifPresent(employee -> db_patient.get().setAdmittedBy(employee));
            }
            patientRepository.save(db_patient.get());
        }

    }
}
