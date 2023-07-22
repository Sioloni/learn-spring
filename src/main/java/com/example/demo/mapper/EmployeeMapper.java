package com.example.demo.mapper;

import com.example.demo.service.AddressServiceDao;
import com.example.demo.service.PassportServiceDao;
import com.example.demo.model.Address;
import com.example.demo.model.Employee;
import com.example.demo.model.Passport;
import com.example.demo.model.dto.EmployeeDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    private final PassportServiceDao passportService;
    private final PassportMapper passportMapper;

    private final AddressServiceDao addressService;
    private final AddressMapper addressMapper;

    public EmployeeMapper(@Lazy PassportServiceDao passportService,@Lazy PassportMapper passportMapper,@Lazy AddressServiceDao addressService,@Lazy AddressMapper addressMapper) {
        this.passportService = passportService;
        this.passportMapper = passportMapper;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }


    public EmployeeDto toDto(Employee entity) {
        return new EmployeeDto
                (
                        entity.getId(),
                        entity.getAge(),
                        entity.getName(),
                        entity.getSurname(),
                        entity.getPassport().stream()
                                .map(Passport::getId).collect(Collectors.toList()),
                        entity.getAddress().stream()
                                .map(Address::getId).collect(Collectors.toList())
                );
    }

    public List<Employee> toEntity(List<EmployeeDto> dto){
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }
    public Employee toEntity(EmployeeDto dto){
        return new Employee
                (
                        dto.getId(),
                        dto.getAge(),
                        dto.getName(),
                        dto.getSurname(),



                        passportMapper.toEntity(passportService.getAllById(dto.getPassportId())),
                        addressMapper.toEntity(addressService.getAllById(dto.getId()))
        );
    }

    public List<EmployeeDto> toDto(List<Employee> entity){
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }




    public Employee update(Employee entity, EmployeeDto dto){
        Employee employee = toEntity(dto);
        if(employee.getAge() != null){
            entity.setAge(employee.getAge());
        }
        if(employee.getName() != null && !entity.getName().isEmpty()){
            entity.setName(employee.getName());
        }
        if(employee.getSurname() != null && !entity.getSurname().isEmpty()){
            entity.setSurname(employee.getSurname());
        }
        if(employee.getPassport() != null && employee.getPassport().size() != 0){
            entity.setPassport(employee.getPassport());
        }
        if(employee.getAddress() != null && employee.getAddress().size() != 0){
            entity.setAddress(employee.getAddress());
        }
        return entity;
    }

}
