package org.example.controller;

import org.example.entity.Employee;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody Employee emp) {
        long empId = employeeService.addNewEmployee(emp);
        return new ResponseEntity<>("Employee with id " + empId + " created succesfully", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getEmployee() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>("Employee list is empty", HttpStatus.OK);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long empId) {
        Employee emp = employeeService.getEmployeeById(empId);
        if (emp == null) {
            return new ResponseEntity<>("Employee with id "+empId+ " not found.", HttpStatus.OK);
        }
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable long empId) {
        long employeeId = employeeService.updateEmployee(updatedEmployee, empId);
        if (employeeId == -1) {
            return new ResponseEntity<>("Employee with id " + empId + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Employee with id " + employeeId + " updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long empId) {
        long employeeId = employeeService.deleteEmployee(empId);
        if (employeeId == -1) {
            return new ResponseEntity<>("Employee with id " + empId + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Employee with id " + employeeId + " deleted successfully", HttpStatus.OK);
    }
}