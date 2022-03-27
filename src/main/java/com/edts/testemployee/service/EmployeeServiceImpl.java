package com.edts.testemployee.service;

import com.edts.testemployee.model.Employee;
import com.edts.testemployee.repository.EmployeeRepository;
import com.edts.testemployee.repository.EmployeeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeRepositoryCustom employeeRepositoryCustom;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,EmployeeRepositoryCustom employeeRepositoryCustom){
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryCustom = employeeRepositoryCustom;
    }
    @Override
    public Employee createEmployee(Employee employee) throws Exception {
        Employee employeeDB= find(employee.getId());
        if (employeeDB!=null){
            throw new Exception("Employee with id (" + employee.getId()+") is exist");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) throws Exception {
        Employee employeeDB= find(employee.getId());
        if (employeeDB==null){
            throw new Exception("Employee with id (" +employee.getId()+") not exist");
        }
        employeeDB.setName(employee.getName());
        employeeDB.setSalary(employee.getSalary());
        employeeDB.setGrade(employee.getGrade());
        return employeeRepository.save(employeeDB);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> executeCustomSelectQuery(Map<String, Object> requestData, Class<Employee> employeeClass, boolean like, boolean and) {
        return employeeRepositoryCustom.executeCustomSelectQuery(requestData, employeeClass, like, and);
    }

    @Override
    public List<Employee> getTotalBonus(List<Employee> resultListEmployee) {
        List<Employee> responseListEmployee = new ArrayList<>();
        int totalBonus;
        for (Employee employee:resultListEmployee){
            if (employee.getGrade() == Employee.MANAJER){
                totalBonus = employee.getSalary()+(employee.getSalary()*10/100);
                employee.setTotalBonus(totalBonus);
            }
            if (employee.getGrade() == Employee.SUPERVISOR){
                totalBonus = employee.getSalary()+(employee.getSalary()*6/100);
                employee.setTotalBonus(totalBonus);
            }
            if (employee.getGrade() == Employee.STAFF){
                totalBonus = employee.getSalary()+(employee.getSalary()*3/100);
                employee.setTotalBonus(totalBonus);
            }
            responseListEmployee.add(employee);
        }
        return responseListEmployee;
    }

    private Employee find(Long id) throws Exception {
        return employeeRepository.findById(id).orElse(null);
    }
}
