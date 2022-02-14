package com.mphasis.employeemanagement.controller;

import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.payload.ApiResponse;
import com.mphasis.employeemanagement.payload.EmployeeDto;
import com.mphasis.employeemanagement.service.EmployeeService;
import com.mphasis.employeemanagement.service.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;

    private final IEmployeeService service;

    public EmployeeController(EmployeeService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public String getWelcomeMessage() {
        return "Welcome to Employee Management System";
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> addEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
        Employee empRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = service.addEmployee(empRequest);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
        Employee employee = service.getEmployeeById(id);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        return service.getAllEmployees()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable int id) {
        Employee empRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = service.updateEmployee(id, empRequest);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable int id) {
        service.deleteEmployeeById(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Employee Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/employee/{id}/{salary}")
    public ResponseEntity<EmployeeDto> updateEmployeeSalaryById(@PathVariable(name = "id")int id,
                                                                @PathVariable(name = "salary") double salary) {
        Employee employee = service.updateEmployeeSalaryById(id, salary);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @GetMapping("/getSalary/{id}")
    public ResponseEntity<Double> getEmployeeSalaryById(@PathVariable int id) {
        double salary = service.getEmployeeSalaryById(id);

        return ResponseEntity.ok().body(salary);
    }

    @GetMapping("/employees/{department}")
    public List<EmployeeDto> getAllEmployeesByDepartment(@PathVariable String department) {
        List<Employee> employeesByDepartment = service.getEmployeesByDepartment(department);
        return employeesByDepartment.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .sorted(Comparator.comparing(EmployeeDto::getId))
                .collect(Collectors.toList());
    }
}
