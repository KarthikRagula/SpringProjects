package org.example.serviceImpl;

import org.example.entity.Employee;
import org.example.service.EmployeeService;

import java.util.List;

public class DBEmployeeServiceImpl implements EmployeeService {

    @Override
    public long addNewEmployee(Employee employee) {
        return 0;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public Employee getEmployeeById(long empId) {
        return null;
    }

    @Override
    public long updateEmployee(Employee employee, long empId) {
        return 0;
    }

    @Override
    public long deleteEmployee(long empId) {
        return 0;
    }
}
