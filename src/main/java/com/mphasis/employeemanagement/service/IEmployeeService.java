package com.mphasis.employeemanagement.service;

import com.mphasis.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IEmployeeService {

    Employee addEmployee(Employee employeeDetails);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(int id, Employee employeeDetails);

    void deleteEmployeeById(int id);

    Employee updateEmployeeSalaryById(int id, double salary);

    double getEmployeeSalaryById(int id);
}
