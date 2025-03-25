package org.example.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Employee {
    private int empId;
    private String empName;
    private String phone;

    @PostConstruct
    public void init() {
        System.out.println("MyService is initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MyService is being destroyed");
    }

    public Employee(int empId, String empName, String phone) {
        this.empId = empId;
        this.empName = empName;
        this.phone = phone;
    }

    public Employee() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
