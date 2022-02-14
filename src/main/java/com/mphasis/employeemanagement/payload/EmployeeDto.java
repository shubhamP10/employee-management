package com.mphasis.employeemanagement.payload;

import lombok.Data;

@Data
public class EmployeeDto {
    private int id;
    private String name;
    private double salary;
    private String department;
}
