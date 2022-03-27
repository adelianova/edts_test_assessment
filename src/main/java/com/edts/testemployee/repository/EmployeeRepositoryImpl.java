package com.edts.testemployee.repository;

import com.edts.testemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.*;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
    @Autowired
    protected EntityManager entityManager;
    @Override
    public List<Employee> executeCustomSelectQuery(Map<String, Object> requestData, Class<Employee> employeeClass, boolean like, boolean and) {
        Query query = entityManager.createQuery(createSelectQuery(requestData, employeeClass, like, and));
        List<Employee> sourceList = query.getResultList();
        return sourceList;
    }

    private String createSelectQuery(Map<String, Object> requestData, Class<Employee> employeeClass, boolean like, boolean and) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM ").append(employeeClass.getSimpleName());
        if (requestData != null) {
            sb.append(" a");
            // Criteria
            Map<String, Object> criteria = (Map<String, Object>) requestData.get("criteria");

            if (criteria != null && criteria.size() > 0) {
                for (Object key : criteria.keySet()) {
                    int i = 0;
                    sb.append(" WHERE ");
                    i++;
                    if (key.equals("ID")) {
                        sb.append("a.id like '%");
                        sb.append(criteria.get("ID"));
                        sb.append("%'");
                    }else if (key.equals("Salary")) {
                        sb.append("a.salary like '%");
                        sb.append(criteria.get("Salary"));
                        sb.append("%'");
                    }else if (key.equals("Grade")) {
                        sb.append("a.grade like '%");
                        sb.append(criteria.get("Grade"));
                        sb.append("%'");
                    }else if (key.equals("Name")) {
                        sb.append("a.name like '%");
                        sb.append(criteria.get("Name"));
                        sb.append("%'");
                    }
                    if (i < criteria.size()) {
                        if (and)
                            sb.append(" AND ");
                        else
                            sb.append(" OR ");
                    }
                }
            }

        }
        System.out.println(sb.toString());

        return sb.toString();
    }
}
