package com.mphasis.employeemanagement.repository;

import com.mphasis.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
