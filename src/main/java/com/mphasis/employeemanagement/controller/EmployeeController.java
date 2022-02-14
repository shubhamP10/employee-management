package com.mphasis.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @GetMapping("/")
    public String getWelcomeMessage() {
        return "Welcome to Employee Management System";
    }
}
