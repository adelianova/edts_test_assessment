package com.edts.testemployee.repository;

import com.edts.testemployee.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepositoryCustom {
    List<Employee> executeCustomSelectQuery(Map<String, Object> requestData, Class<Employee> employeeClass, boolean like, boolean and);
}
