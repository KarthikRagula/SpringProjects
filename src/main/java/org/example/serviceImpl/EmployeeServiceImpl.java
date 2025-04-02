package org.example.serviceImpl;

import org.example.entity.Employee;
import org.example.service.EmployeeService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> listOfEmployees = new ArrayList<>();

    public synchronized long addNewEmployee(Employee employee) {
        listOfEmployees.add(employee);
        return employee.getEmpId();
    }

    @Override
    public synchronized List<Employee> getAllEmployees() {
        return new ArrayList<>(listOfEmployees);
    }

    @Override
    public synchronized Employee getEmployeeById(long empId) {
        for (Employee emp : listOfEmployees) {
            if (emp.getEmpId() == empId) {
                return emp;
            }
        }
        return null;
    }

    public synchronized long updateEmployee(Employee updatedEmp, long empId) {
        for (Employee emp : listOfEmployees) {
            if (emp.getEmpId() == empId) {
                emp.setName(updatedEmp.getName());
                emp.setAge(updatedEmp.getAge());
                emp.setPhone(updatedEmp.getPhone());
                return empId;
            }
        }
        return -1;
    }

    public synchronized long deleteEmployee(long empId) {
        Iterator<Employee> iterator = listOfEmployees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getEmpId() == empId) {
                iterator.remove();
                return empId;
            }
        }
        return -1;
    }
}