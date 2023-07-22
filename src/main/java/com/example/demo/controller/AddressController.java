package com.example.demo.controller;

import com.example.demo.service.AddressServiceDao;
import com.example.demo.model.dto.AddressDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    @Autowired
    private AddressServiceDao service;

    @GetMapping
    public List<AddressDto> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public AddressDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody AddressDto dto) {
        service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody AddressDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
