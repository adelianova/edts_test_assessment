package com.edts.testemployee;

import com.edts.testemployee.controller.EmployeeController;
import com.edts.testemployee.model.Employee;
import com.edts.testemployee.validator.EmployeeValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestEmployeeApplicationTests {

    @Autowired
    EmployeeController employeeController;
    @Test
    void contextLoads() {
    }

    @Test
    void whenInvalidInputGrade() throws Exception {
        Employee employee = new Employee(10005, "Adelia", 10000, 10);
        ResponseEntity<Map<String, Object>> responseEntity = employeeController.create(employee);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    @Test
    void whenCreateNameIsNull() throws Exception {
        Employee employee = new Employee(10003, null, 10000, 2);
        ResponseEntity<Map<String, Object>> responseEntity = employeeController.create(employee);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    @Test
    void whenUpdateEmployeeSuccess() throws Exception {
        Employee employee = new Employee(10002, "Stevenson Black", 14000, 2);
        ResponseEntity<Map<String, Object>> responseEntity = employeeController.update(employee);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void whenCreateEmployeeSuccess() throws Exception {
        Employee employee = new Employee(10015, "Cimeng", 5000, 3);
        ResponseEntity<Map<String, Object>> responseEntity = employeeController.create(employee);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void whenUpdateIdIsNull() throws Exception {
        Employee employee = new Employee(10003, null, 10000, 2);
        ResponseEntity<Map<String, Object>> responseEntity = employeeController.create(employee);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    @Test
    void whenRetrieve() throws Exception {
        Map<String,Object> request=null;
        ResponseEntity<List<Employee>> responseEntity = employeeController.retrieve(request);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

}
