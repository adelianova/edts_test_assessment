package com.edts.testemployee.service;

import com.edts.testemployee.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService{
    Employee createEmployee(Employee employee) throws Exception;

    Employee updateEmployee(Employee employee) throws Exception;

    List<Employee> findAllEmployee();

    List<Employee> executeCustomSelectQuery(Map<String, Object> requestData, Class<Employee> employeeClass, boolean b, boolean b1);

    List<Employee> getTotalBonus(List<Employee> responseListEmployee);
}
