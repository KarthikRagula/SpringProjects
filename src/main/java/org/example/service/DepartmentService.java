package org.example.service;

import org.example.entity.Department;

import java.util.List;

public interface DepartmentService {

    long addNewDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(long deptId);

    long updateDepartment(Department department, long deptId);

    long deleteDepartment(long deptId);
}
