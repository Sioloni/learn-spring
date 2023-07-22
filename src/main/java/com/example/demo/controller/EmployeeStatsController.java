package com.example.demo.controller;

import com.example.demo.model.EmployeeStats;
import com.example.demo.service.EmployeeStatsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address/employee/state")
@AllArgsConstructor
public class EmployeeStatsController {
    @Autowired
    private EmployeeStatsService service;


    @GetMapping
    public EmployeeStats get() {
        return service.get();
    }
}
