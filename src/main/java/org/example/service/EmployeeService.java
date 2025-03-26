package org.example.service;

import org.example.entity.Employee;

import java.util.List;

public interface EmployeeService {
    long addNewEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long empId);

    long updateEmployee(Employee employee, long empId);

    long deleteEmployee(long empId);
}