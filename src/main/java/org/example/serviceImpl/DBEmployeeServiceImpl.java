package org.example.serviceImpl;


import org.example.entity.Department;
import org.example.entity.Employee;
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
            return -2;
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
        return hibernateTemplate.get(Employee.class, empId);
    }

    @Override
    public long updateEmployee(Employee updateEmployee, long empId) {
        Employee employee = hibernateTemplate.get(Employee.class, empId);
        Department department = hibernateTemplate.get(Department.class, updateEmployee.getDepartment().getDeptId());
        if (employee == null) {
            return -1;
        } else if (department == null) {
            return -2;
        }
        updateEmployee.setEmpId(empId);
        hibernateTemplate.merge(updateEmployee);
        return empId;
    }

    @Override
    public long deleteEmployee(long empId) {
        Employee emp = hibernateTemplate.get(Employee.class, empId);
        if (emp != null) {
            hibernateTemplate.delete(emp);
            return empId;
        }
        return -1;
    }
}
