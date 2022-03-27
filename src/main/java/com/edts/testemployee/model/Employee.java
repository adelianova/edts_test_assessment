package com.edts.testemployee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.*;

@Entity
@Table(name = "employee")
@JsonInclude
@JsonIgnoreProperties(value = {"totalBonus"}, ignoreUnknown = true)
public class Employee{

    public static final int MANAJER = 1;
    public static final int SUPERVISOR = 2;
    public static final int STAFF = 3;

    @Id
    @Column(name = "id")
    @JsonProperty("ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonProperty("Name")
    public String name;

    @JsonProperty("Salary")
    public int salary;

    @JsonProperty("Grade")
    private int grade;

    @Transient
    @JsonProperty("TotalBonus")
    private int totalBonus;

    public Employee(){

    }
    public Employee(long id, String name, int salary, int grade) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(int totalBonus) {
        this.totalBonus = totalBonus;
    }
}
