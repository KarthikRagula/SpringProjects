package org.example.controller;

import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private Employee employee;

    @GetMapping("/getEmployeeFromXML")
    @ResponseBody
    public Employee getEmployeeFromXML() {
        return employee;
    }

    @GetMapping("/getEmployeeFromJava")
    @ResponseBody
    public Employee getEmployeeFromJava() {
        employee.setEmpId(1);
        employee.setEmpName("Karthik");
        employee.setPhone("8008041620");
        return employee;
    }
}