package com.mphasis.employeemanagement.repository;

import com.mphasis.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT emp FROM Employee emp WHERE emp.department = ?1 ORDER BY ID ASC")
    List<Employee> getEmployeesByDepartment(String department);
}
