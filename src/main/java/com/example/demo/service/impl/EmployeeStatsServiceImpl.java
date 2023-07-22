package com.example.demo.service.impl;

import com.example.demo.mapper.EmployeeRowMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeStats;
import com.example.demo.service.EmployeeStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeStatsServiceImpl implements EmployeeStatsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public EmployeeStats get() {
        return new EmployeeStats(
                getMaxAgeEmployee(),
                getMinAgeEmployee(),
                getAmountOfEmployee()
        );
    }

    @Override
    public Employee getMaxAgeEmployee() {
        Employee entity = jdbcTemplate.queryForObject("select * from employee where age = (select max(age) from employee)", new EmployeeRowMapper());
        return entity;
    }

    @Override
    public Employee getMinAgeEmployee() {
      Employee entity = jdbcTemplate.queryForObject("select * from employee where age = (select min(age) from employee)", new EmployeeRowMapper());
        return entity;
    }

    @Override
    public Integer getAmountOfEmployee() {
        Integer count =  jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
        return count;
    }
}
