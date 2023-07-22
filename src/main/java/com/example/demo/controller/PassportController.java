package com.example.demo.controller;

import com.example.demo.service.PassportServiceDao;
import com.example.demo.model.dto.PassportDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address/employee/passport")
@AllArgsConstructor
public class PassportController {
    @Autowired
    private PassportServiceDao service;

    @GetMapping
    public List<PassportDto> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public PassportDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody PassportDto entity) {
        service.create(entity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PassportDto entity) {
        service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
