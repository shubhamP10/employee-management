package com.mphasis.employeemanagement.service;

import com.mphasis.employeemanagement.exception.EmployeeNotFoundException;
import com.mphasis.employeemanagement.exception.DepartmentNotFoundException;
import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Employee addEmployee(Employee employeeDetails) {
        return repository.save(employeeDetails);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee updateEmployee(int id, Employee employeeDetails) {
        repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
        employeeDetails.setId(id);
        return repository.save(employeeDetails);
    }

    @Override
    public void deleteEmployeeById(int id) {
        repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repository.deleteById(id);
    }

    @Override
    public Employee updateEmployeeSalaryById(int id, double salary) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setSalary(salary);
        return repository.save(employee);
    }

    @Override
    public double getEmployeeSalaryById(int id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getSalary();
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        if (repository.getEmployeesByDepartment(department).isEmpty())
            throw new DepartmentNotFoundException(department);
        return repository.getEmployeesByDepartment(department);
    }

}
