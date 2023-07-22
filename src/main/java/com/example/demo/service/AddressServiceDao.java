package com.example.demo.service;

import com.example.demo.model.dto.AddressDto;

import java.util.List;

public interface AddressServiceDao {

    List<AddressDto> get();

    AddressDto get(Long id);

    void create(AddressDto dto);

    void update(Long id, AddressDto dto);

    void delete(Long id);
    List<AddressDto> getAllById(Long id);
}
