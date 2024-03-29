package com.hospital.hospitalmanagment.Controllers;

import com.hospital.hospitalmanagment.Services.EmployeeService;
import com.hospital.hospitalmanagment.model.*;
import com.hospital.hospitalmanagment.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllDoctors(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Employee> getDoctorById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("get-by-status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Employee>>getDoctorByStatus(@RequestParam Status status){
        List<Employee> doctors_db= employeeService.getEmployeeByStatus(status);
        return doctors_db.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(doctors_db);


    }
    @GetMapping("get-by-department")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Employee>>getDoctorByDepartment(@RequestParam String department){
        List<Employee> doctors_db= employeeService.getEmployeeByDepartment(department);
        return doctors_db.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(doctors_db);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDoctor(@RequestBody Employee employee){
        employeeService.addDoctor(employee);
    }

    @PatchMapping("/change-status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeDoctorStatus(@RequestParam int employeeId, @RequestBody EmployeeStatusOnlyDto new_status){
        employeeService.changeDoctorStatus(employeeId,new_status.getStatus());
    }

    @PatchMapping("/change-department")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDoctorDepartment(@RequestParam int employeeId, @RequestBody EmployeeDepartmentOnlyDto new_department){
        employeeService.updateDoctorDepartment(employeeId, new_department.getDepartment());
    }

}
