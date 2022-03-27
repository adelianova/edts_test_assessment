package com.edts.testemployee.validator;

import com.edts.testemployee.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EmployeeValidator {
    String _REGEX_GRADE = "[1-3]";

    public void validate(Employee employee) throws Exception {
        checkID(employee.getId());
        checkName(employee.getName());
        checkSalary(employee.getSalary());
        checkGrade(employee.getGrade());
    }

    private void checkGrade(int grade) throws Exception {
        notNull(grade,"Grade");
        isMax(String.valueOf(grade),1,"Grade");
        if (!String.valueOf(grade).matches(_REGEX_GRADE)){
            throw new Exception("Grade input is invalid");
        }
    }

    private void checkSalary(int salary) throws Exception {
        notNull(salary,"Salary");
    }

    private void checkName(String name) throws Exception {
        notBlank(name,"Name");
        isMax(name,50,"Name");
    }

    private void checkID(Long id) throws Exception {
        notNull(id,"ID");
    }

    protected void notNull(Object value, String field) throws Exception {
        if (value == null) {
            throw new Exception(field + " is required");
        }
    }

    protected void notZero(Object value, String field) throws Exception {
        this.notNull(value, field);
        if (value instanceof Integer && value.equals(0)) {
            throw new Exception(field + " is required");
        }
    }
    protected void notBlank(String value, String field) throws Exception {
        if (StringUtils.isEmpty(value)) {
            throw new Exception(field + " is required");
        }
    }
    protected void isMax(String value, int length, String field) throws Exception {
        if (!StringUtils.isEmpty(value) && value.length() > length) {
            throw new Exception(field + " max length is " + length);
        }
    }
}
