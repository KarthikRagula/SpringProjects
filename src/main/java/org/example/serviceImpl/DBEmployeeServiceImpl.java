package org.example.serviceImpl;


import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.request.EmployeeRequest;
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
    public long addNewEmployee(EmployeeRequest employeeRequest) {
        Department department = hibernateTemplate.get(Department.class, employeeRequest.getDeptId());
        if (department == null) {
            return -2;
        }
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setAge(employeeRequest.getAge());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDepartment(department);
        hibernateTemplate.save(employee);
        return employee.getEmpId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return hibernateTemplate.loadAll(Employee.class);
    }

    @Override
    public Employee getEmployeeById(long empId) {
        return hibernateTemplate.get(Employee.class, empId); // ✅ Corrected
    }

    @Override
    public long updateEmployee(EmployeeRequest updatedEmployeeRequest, long empId) {
        Employee employee = hibernateTemplate.get(Employee.class, empId);
        Department department = hibernateTemplate.get(Department.class, updatedEmployeeRequest.getDeptId());
        if (employee == null) {
            return -1;
        } else if (department == null) {
            return -2;
        }
        employee.setName(updatedEmployeeRequest.getName());
        employee.setAge(updatedEmployeeRequest.getAge());
        employee.setPhone(updatedEmployeeRequest.getPhone());
        employee.setDepartment(department);
        hibernateTemplate.update(employee);
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
