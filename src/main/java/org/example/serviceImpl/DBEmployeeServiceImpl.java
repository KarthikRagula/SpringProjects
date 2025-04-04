package org.example.serviceImpl;


import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.exception.DepartmentNotFoundException;
import org.example.exception.EmployeeNotFoundException;
import org.example.service.DBEmployeeService;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class DBEmployeeServiceImpl implements DBEmployeeService {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public long addNewEmployee(Employee employee) {
        Department department = hibernateTemplate.get(Department.class, employee.getDepartment().getDeptId());
        if (department == null) {
            throw new DepartmentNotFoundException("Department with the Id " + employee.getDepartment().getDeptId() + " is not found");
        }
        hibernateTemplate.save(employee);
        return employee.getEmpId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return hibernateTemplate.loadAll(Employee.class);
    }

    @Override
    public Employee getEmployeeById(long empId) {
        Employee emp = hibernateTemplate.get(Employee.class, empId);
        if (emp == null) {
            throw new EmployeeNotFoundException("Employee with Id " + empId + " not found");
        }
        return hibernateTemplate.get(Employee.class, empId);
    }

    @Override
    public long updateEmployee(Employee updateEmployee, long empId) {
        Employee employee = hibernateTemplate.get(Employee.class, empId);
        Department department = hibernateTemplate.get(Department.class, updateEmployee.getDepartment().getDeptId());
        if (employee == null) {
            throw new DepartmentNotFoundException("Employee with the Id " + empId + " is not found");
        } else if (department == null) {
            throw new DepartmentNotFoundException("Department with the Id " + updateEmployee.getDepartment().getDeptId() + " is not found");
        }
        updateEmployee.setEmpId(empId);
        hibernateTemplate.merge(updateEmployee);
        return empId;
    }

    @Override
    public long deleteEmployee(long empId) {
        Employee emp = hibernateTemplate.get(Employee.class, empId);
        if (emp == null) {
            throw new DepartmentNotFoundException("Employee with the Id " + empId + " is not found");
        }
        hibernateTemplate.delete(emp);
        return empId;
    }
}
