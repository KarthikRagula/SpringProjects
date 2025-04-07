package org.example.serviceImpl;


import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.exception.DepartmentNotFoundException;
import org.example.exception.EmployeeNotFoundException;
import org.example.service.DBEmployeeService;
import org.example.service.DepartmentService;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class DBEmployeeServiceImpl implements DBEmployeeService {

    @Autowired
    private DepartmentService departmentService;

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        Department department = departmentService.getDepartmentById(employee.getDepartment().getDeptId());
        if (department == null) {
            throw new DepartmentNotFoundException("Department with the Id " + employee.getDepartment().getDeptId() + " is not found");
        }
        hibernateTemplate.save(employee);
        return employee;
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
    public Employee updateEmployee(Employee updateEmployee) {
        Employee employee = getEmployeeById(updateEmployee.getEmpId());
        Department department = departmentService.getDepartmentById(updateEmployee.getDepartment().getDeptId());
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with the Id " + updateEmployee.getEmpId() + " is not found");
        } else if (department == null) {
            throw new DepartmentNotFoundException("Department with the Id " + updateEmployee.getDepartment().getDeptId() + " is not found");
        }
//        Update: if you are sure that the session does not contains an already persistent instance with the same identifier,then use update to save the data in hibernate
//        Merge: if you want to save your modifications at any time with out knowing about the state of an session, then use merge() in hibernate.
        hibernateTemplate.merge(updateEmployee);
        return updateEmployee;
    }

    @Override
    public long deleteEmployee(long empId) {
        Employee emp = getEmployeeById(empId);
        if (emp == null) {
            throw new EmployeeNotFoundException("Employee with the Id " + empId + " is not found");
        }
        hibernateTemplate.delete(emp);
        return empId;
    }
}
