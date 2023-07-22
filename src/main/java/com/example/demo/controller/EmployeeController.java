package com.example.demo.controller;

import com.example.demo.model.EmployeeFilter;
import com.example.demo.service.EmployeeServiceDao;
import com.example.demo.model.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address/employee")
@AllArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeServiceDao service;

    @GetMapping
    public List<EmployeeDto> get() {
        return service.get();
    }

    @PostMapping("/filter")
    public List<EmployeeDto> getFilter(@RequestBody EmployeeFilter filter){
        return service.getFilter(filter);
    }

    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody EmployeeDto entity) {
        service.create(entity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EmployeeDto entity) {
        service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
