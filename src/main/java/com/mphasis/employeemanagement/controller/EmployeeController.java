package com.mphasis.employeemanagement.controller;

import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    IEmployeeService service;

    @GetMapping("/")
    public String getWelcomeMessage() {
        return "Welcome to Employee Management System";
    }

    @PostMapping("/employee")
    public Employee addEmployeeDetails(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }
}
