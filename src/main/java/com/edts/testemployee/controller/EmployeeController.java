package com.edts.testemployee.controller;

import com.edts.testemployee.model.Employee;
import com.edts.testemployee.service.EmployeeService;
import com.edts.testemployee.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeValidator employeeValidator;

    public EmployeeController(EmployeeService employeeService, EmployeeValidator employeeValidator) {
        this.employeeService = employeeService;
        this.employeeValidator = employeeValidator;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Employee employee){
        Map<String,Object> response = new HashMap<>();
        try {
            employeeValidator.validate(employee);
            employee = employeeService.createEmployee(employee);
            response.put("ResponseMessage", "Employee data has been created");
            response.put("Result",employee);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            response.put("ResponseMessage",e.getMessage());
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        }

    }
    @PostMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody Employee employee){
        Map<String,Object> response = new HashMap<>();
        try {
            employeeValidator.validate(employee);
            employee = employeeService.updateEmployee(employee);
            response.put("ResponseMessage", "Employee data has been updated");
            response.put("Result",employee);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            response.put("ResponseMessage",e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @PostMapping(value = "/retrieve")
    public ResponseEntity<List<Employee>> retrieve(@RequestBody Map<String,Object> requestData){
        List<Employee> responseListEmployee;
        List<Employee> resultListEmployee;
        if (requestData!=null){
            resultListEmployee = employeeService.executeCustomSelectQuery(requestData, Employee.class, true, true);
        }else {
            resultListEmployee = employeeService.findAllEmployee();
        }
        responseListEmployee = employeeService.getTotalBonus(resultListEmployee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseListEmployee);
    }
}
