package org.example.service;

import org.example.entity.Employee;
import org.example.request.EmployeeRequest;

import java.util.List;

public interface DBEmployeeService {
    long addNewEmployee(EmployeeRequest employeeRequest);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long empId);

    long updateEmployee(EmployeeRequest updatedEmployeeRequest, long empId);

    long deleteEmployee(long empId);
}
