package org.example.serviceImpl;

import org.springframework.transaction.annotation.Transactional;
import org.example.entity.Employee;

import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class DBEmployeeServiceImpl implements EmployeeService {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public long addNewEmployee(Employee employee) {
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
    public long updateEmployee(Employee employee, long empId) {
        Employee emp = hibernateTemplate.get(Employee.class, empId);
        if (emp != null) {
            emp.setName(employee.getName());
            emp.setAge(employee.getAge());
            emp.setPhone(employee.getPhone());
            hibernateTemplate.update(emp);
            return empId;
        }
        return -1;
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
