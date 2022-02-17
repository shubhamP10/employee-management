package com.mphasis.employeemanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mphasis.employeemanagement.model.Employee;
import com.mphasis.employeemanagement.payload.ApiResponse;
import com.mphasis.employeemanagement.payload.EmployeeDto;
import com.mphasis.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {

    EmployeeDto employeeDto;
    Employee employee;
    List<Employee> employeeList = new ArrayList<>();
    String uri = "https://localhost:8080/api/v1/";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        employeeList.add(new Employee(1, "Shubham", 30000.00, "Design"));
        employeeList.add(new Employee(2, "Sunil", 40000.5, "Development"));
        employeeList.add(new Employee(3, "Avinash", 30330.00, "Testing"));

        employeeDto = new EmployeeDto(1, "Shubham", 30000.00, "Design");
        employee = new Employee(1, "Shubham", 30000.00, "Design");
    }

    @AfterEach
    void tearDown() {
        employeeList.clear();
    }

    @Test
    void getWelcomeMessage() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals("Welcome to Employee Management System", response);
    }

    @Test
    void addEmployeeDetails() throws Exception {
        String uri1 = this.uri += "employee";
        String inputJson = this.mapToJson(employeeDto);

        when(service.addEmployee(Mockito.any(Employee.class))).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri1)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString();

        assertThat(outputJson).isEqualTo(inputJson);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    void getEmployeeById() throws Exception {
        String requestUri = this.uri += "employee/2";
        when(service.getEmployeeById(anyInt())).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(requestUri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(employee);
        String outputJson = result.getResponse().getContentAsString();

        assertThat(outputJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }

    @Test
    void getAllEmployees() throws Exception {
        String requestUri = this.uri += "employees";

        when(service.getAllEmployees()).thenReturn(employeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(requestUri)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(employeeList);
        String responseJson = result.getResponse().getContentAsString();

        assertThat(responseJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void updateEmployeeById() throws Exception {
        String requestUri = this.uri += "employee/2";
        String requestJson = this.mapToJson(employeeDto);

        when(service.updateEmployee(anyInt(), Mockito.any(Employee.class))).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(requestUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String responseJson = result.getResponse().getContentAsString();

        assertThat(responseJson).isEqualTo(requestJson);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void deleteEmployeeById() throws Exception {
        String requestUri = this.uri += "employee/2";
        ApiResponse apiResponse = new ApiResponse(true, "Employee Deleted Successfully", HttpStatus.OK);

        doNothing().when(service).deleteEmployeeById(anyInt());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(requestUri).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String responseJson = result.getResponse().getContentAsString();
        assertThat(responseJson).isEqualTo(this.mapToJson(apiResponse));
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void updateEmployeeSalaryById() throws Exception {
        String requestUri = this.uri += "employee/2/4500.0";
        String expectedJson = this.mapToJson(employeeDto);
        when(service.updateEmployeeSalaryById(anyInt(), Mockito.anyDouble())).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(requestUri).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String responseJson = result.getResponse().getContentAsString();

        assertThat(responseJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void getEmployeeSalaryById() throws Exception {
        String requestUri = this.uri += "getSalary/2";

        when(service.getEmployeeSalaryById(anyInt())).thenReturn(25000.0);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(requestUri).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String responseJson = result.getResponse().getContentAsString();
        assertThat(responseJson).isEqualTo("25000.0");
    }

    @Test
    void getAllEmployeesByDepartment() throws Exception {
        String requestUri = this.uri += "employees/Design";

        when(service.getEmployeesByDepartment(Mockito.anyString())).thenReturn(employeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(requestUri).accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(employeeList)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(employeeList));
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}