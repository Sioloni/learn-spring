package com.example.demo.mapper;

import com.example.demo.model.Address;
import com.example.demo.model.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressMapper {
    public AddressDto toDto(Address entity) {
        return new AddressDto
                (
                        entity.getId(),
                        entity.getAddress()
                );
    }

    public Address toEntity(AddressDto dto) {
        return new Address
                (
                        dto.getId(),
                        dto.getAddress()
                );
    }

    public List<AddressDto> toDto(List<Address> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Address> toEntity(List<AddressDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
