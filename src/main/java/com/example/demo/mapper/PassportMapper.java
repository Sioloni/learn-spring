package com.example.demo.mapper;

import com.example.demo.service.EmployeeServiceDao;
import com.example.demo.model.Passport;
import com.example.demo.model.dto.PassportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PassportMapper {
    private final EmployeeServiceDao employeeService;
    private final EmployeeMapper employeeMapper;

    public PassportDto toDto(Passport entity) {
        return new PassportDto(
                entity.getId(),
                entity.getNumber(),
                entity.getEmployee().getId(),
                entity.getEmployee().getName()
        );
    }

    public Passport toEntity(PassportDto dto) {
        return new Passport(
                dto.getId(),
                dto.getNumber(),
                employeeService.getEntity(dto.getEmployeeId())
        );
    }

    public List<PassportDto> toDto(List<Passport> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Passport> toEntity(List<PassportDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public Passport update(Passport entity, PassportDto dto) {
        Passport passport = toEntity(dto);
        if(passport.getNumber() != null && !entity.getNumber().isEmpty()){
            entity.setNumber(passport.getNumber());
        }
        if(passport.getEmployee().getId() != null && passport.getEmployee().getName() != null && !passport.getEmployee().getName().isEmpty()){
            entity.setEmployee(employeeMapper.toEntity(employeeService.getByIdAndName(passport.getEmployee().getId(), passport.getEmployee().getName())));
        }
        return entity;
    }
}
