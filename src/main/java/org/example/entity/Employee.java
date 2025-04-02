package org.example.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Employee {

    public static long idCounter = 1;

    @Id
    private long empId;
    private String name;
    private int age;
    private String phone;

    public Employee(long empId, String name, int age, String phone) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Employee(){

    }

    @PostConstruct
    public void init() {
        System.out.println("MyService is initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MyService is being destroyed");
    }

    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        Employee.idCounter = idCounter;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId && age == employee.age && Objects.equals(name, employee.name) && Objects.equals(phone, employee.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, name, age, phone);
    }
}