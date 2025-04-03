package org.example.controller;

import org.example.entity.Employee;
import org.example.request.EmployeeRequest;
import org.example.response.ResponseMessage;
import org.example.service.DBEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dbemployee")
public class DBEmployeeController {

    @Autowired
    private DBEmployeeService dbEmployeeService;

    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        long empId = dbEmployeeService.addNewEmployee(employeeRequest);
        if(empId==-2){
            return new ResponseEntity<>(new ResponseMessage("Department with Id "+ employeeRequest.getDeptId() + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage("Employee with id " + empId + " created succesfully"), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getEmployee() {
        List<Employee> employeeList = dbEmployeeService.getAllEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Employee list is empty"), HttpStatus.OK);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long empId) {
        Employee emp = dbEmployeeService.getEmployeeById(empId);
        if (emp == null) {
            return new ResponseEntity<>(new ResponseMessage("Employee with id " + empId + " not found."), HttpStatus.OK);
        }
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest updatedEmployeeRequest, @PathVariable long empId) {
        long employeeId = dbEmployeeService.updateEmployee(updatedEmployeeRequest, empId);
        if (employeeId == -1) {
            return new ResponseEntity<>(new ResponseMessage("Employee with id " + empId + " not found"), HttpStatus.NOT_FOUND);
        }
        else if(employeeId==-2){
            return new ResponseEntity<>(new ResponseMessage("Department with id " + updatedEmployeeRequest.getDeptId() + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage("Employee with id " + employeeId + " updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long empId) {
        long employeeId = dbEmployeeService.deleteEmployee(empId);
        if (employeeId == -1) {
            return new ResponseEntity<>(new ResponseMessage("Employee with id " + empId + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage("Employee with id " + employeeId + " deleted successfully"), HttpStatus.OK);
    }
}