package org.example.request;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class EmployeeRequest {

    private String name;
    private int age;
    private String phone;
    private long deptId;

    public EmployeeRequest(String name, int age, String phone,long deptId) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.deptId = deptId;
    }

    public EmployeeRequest() {}

    @PostConstruct
    public void init() {
        System.out.println("MyService is initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MyService is being destroyed");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }
}