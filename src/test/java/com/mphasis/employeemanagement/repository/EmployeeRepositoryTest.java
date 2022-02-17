package com.mphasis.employeemanagement.repository;

import com.mphasis.employeemanagement.model.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository repository;

    private final List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        employeeList.add(new Employee(1, "Shubham", 30000.00, "Design"));
        employeeList.add(new Employee(2, "Sunil", 40000.5, "Development"));
        employeeList.add(new Employee(3, "Avinash", 30330.00, "Design"));
    }

    @AfterEach
    void tearDown() {
        employeeList.clear();
    }

    @Test
    void getEmployeesByDepartment() {
        Mockito.when(repository.getEmployeesByDepartment("Design")).thenReturn(employeeList);
        List<Employee> employees = repository.getEmployeesByDepartment("Design");
        Assertions.assertEquals(employeeList, employees);
    }
}