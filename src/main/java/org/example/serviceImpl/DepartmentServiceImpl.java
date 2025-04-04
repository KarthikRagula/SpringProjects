package org.example.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.entity.Department;
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
    public long addNewDepartment(Department department) {
        hibernateTemplate.save(department);
//        int a=5/0;
        return department.getDeptId();
    }

    @Override
    public List<Department> getAllDepartments() {
        return hibernateTemplate.loadAll(Department.class);
    }

    @Override
    public Department getDepartmentById(long deptId) {
        return hibernateTemplate.get(Department.class, deptId);
    }

    @Override
    public long updateDepartment(Department updateDepartment, long deptId) {
        Department department = hibernateTemplate.get(Department.class, deptId);
        if (department != null) {
            updateDepartment.setDeptId(deptId);
            hibernateTemplate.merge(updateDepartment);
            return deptId;
        }
        return -1;
    }

    @Override
    public long deleteDepartment(long deptId) {
        Department department = hibernateTemplate.get(Department.class, deptId);
        if (department != null) {
            hibernateTemplate.delete(department);
            return deptId;
        }
        return -1;
    }
}
