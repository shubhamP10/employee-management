package com.mphasis.employeemanagement.service;

import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    EmployeeRepository repository;

    @Override
    public Employee addEmployee(Employee employeeDetails) {
        return repository.save(employeeDetails);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee updateEmployee(int id, Employee employeeDetails) {
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {

    }
}
