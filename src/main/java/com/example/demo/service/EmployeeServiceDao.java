package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeFilter;
import com.example.demo.model.dto.EmployeeDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EmployeeServiceDao {

    List<EmployeeDto> get();

    EmployeeDto get(Long id);

    void create(EmployeeDto dto);

    void update(Long id, EmployeeDto dto);

    void delete(Long id);

    Employee getEntity(Long id);
    EmployeeDto getByIdAndName(Long id, String name);

    List<EmployeeDto> getFilter(EmployeeFilter filter);
}
