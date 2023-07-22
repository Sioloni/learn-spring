package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeStats;

import java.util.List;

public interface EmployeeStatsService {

    EmployeeStats get();
    Employee getMaxAgeEmployee();
    Employee getMinAgeEmployee();
    Integer getAmountOfEmployee();
}
