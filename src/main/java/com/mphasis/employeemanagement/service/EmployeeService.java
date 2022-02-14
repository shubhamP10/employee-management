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
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) repository.findAll();
    }

    @Override
    public Employee updateEmployee(int id, Employee employeeDetails) {
        if (repository.findById(id).isPresent()) {
            employeeDetails.setId(id);
            return repository.save(employeeDetails);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {
        repository.deleteById(id);
    }
}
