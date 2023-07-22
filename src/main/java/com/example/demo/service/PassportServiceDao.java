package com.example.demo.service;

import com.example.demo.model.dto.PassportDto;

import java.util.List;

public interface PassportServiceDao {

    List<PassportDto> get();

    PassportDto get(Long id);

    void create(PassportDto dto);

    void update(Long id, PassportDto dto);

    void delete(Long id);

    List<PassportDto> getAllById(List<Long> id);
}
