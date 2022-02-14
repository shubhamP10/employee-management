package com.mphasis.employeemanagement.controller;

import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployeeById(@RequestBody Employee employee, @PathVariable int id) {
        return service.updateEmployee(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        service.deleteEmployeeById(id);
    }
}
