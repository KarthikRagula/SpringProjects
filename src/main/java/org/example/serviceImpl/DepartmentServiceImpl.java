package org.example.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.entity.Department;
import org.example.exception.DepartmentNotFoundException;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Department addNewDepartment(Department department) {
        hibernateTemplate.save(department);
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        return hibernateTemplate.loadAll(Department.class);
    }

    @Override
    public Department getDepartmentById(long deptId) {
        Department dep = hibernateTemplate.get(Department.class, deptId);
        if (dep == null) {
            throw new DepartmentNotFoundException("Department with the Id " + deptId + " not found");
        }
        return dep;
    }

    @Override
    public Department updateDepartment(Department updateDepartment, long deptId) {
        Department department = hibernateTemplate.get(Department.class, deptId);
        if (department == null) {
            throw new DepartmentNotFoundException("Department with the Id " + deptId + " is not found");
        }
        updateDepartment.setDeptId(deptId);
        hibernateTemplate.merge(updateDepartment);
        return updateDepartment;
    }

    @Override
    public long deleteDepartment(long deptId) {
        Department department = hibernateTemplate.get(Department.class, deptId);
        if (department == null) {
            throw new DepartmentNotFoundException("Department with the Id " + deptId + " is not found");
        }
        hibernateTemplate.delete(department);
        return deptId;
    }
}
