package com.mphasis.employeemanagement.service;

import com.mphasis.employeemanagement.exception.DepartmentNotFoundException;
import com.mphasis.employeemanagement.exception.EmployeeNotFoundException;
import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceTest {

    private final List<Employee> employeeList = new ArrayList<>();

    @MockBean
    private Employee employee;

    @MockBean
    private EmployeeRepository repository;

    @Autowired
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        employeeList.add(new Employee(1, "Shubham", 30000.00, "Design"));
        employeeList.add(new Employee(2, "Sunil", 40000.5, "Development"));
        employeeList.add(new Employee(3, "Avinash", 30330.00, "Testing"));

        employee = new Employee(2, "Sunil", 40000.5, "Development");
    }

    @AfterEach
    void tearDown() {
        employeeList.clear();
    }

    @Test
    void addEmployee() {
        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, service.addEmployee(employee));
    }

    @Test
    void getEmployeeById() {
        when(repository.findById(2)).thenReturn(Optional.ofNullable(employee));
        assertEquals(employee, service.getEmployeeById(2));
    }

    @Test
    void getAllEmployees() {
        when(repository.findAll()).thenReturn(employeeList);
        assertEquals(3, service.getAllEmployees().size());
    }

    @Test
    void updateEmployee() {
        when(repository.findById(3)).thenReturn(Optional.ofNullable(employee));
        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, service.updateEmployee(3, employee));
    }

    @Test
    void deleteEmployeeById() {
        when(repository.findById(2)).thenReturn(Optional.ofNullable(employee));
        service.deleteEmployeeById(2);
        verify(repository, times(1)).deleteById(2);
    }

    @Test
    void updateEmployeeSalaryById() {
        when(repository.findById(2)).thenReturn(Optional.ofNullable(employee));
        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, service.updateEmployeeSalaryById(2, 20000.04));
    }

    @Test
    void getEmployeeSalaryById() {
        when(repository.findById(2)).thenReturn(Optional.ofNullable(employee));
        assertEquals(40000.5, service.getEmployeeSalaryById(2));
    }

    @Test
    void getEmployeesByDepartment() {
        when(repository.getEmployeesByDepartment("Design")).thenReturn(employeeList);
        assertEquals(3, service.getEmployeesByDepartment("Design").size());
    }

    @Test
    void getWhenEmployeeNotFoundGetException(){
        try {
            when(repository.getEmployeesByDepartment("Test").isEmpty()).thenThrow(new DepartmentNotFoundException("Test"));
            service.getEmployeesByDepartment("Test");
        }catch (Exception exception) {
            assertTrue(exception instanceof DepartmentNotFoundException);
        }


    }
}