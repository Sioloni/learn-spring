package com.example.demo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private Byte age;
    private String name;
    private String surname;
    private List<Long> passportId = new ArrayList<>();
    private List<Long> addressId = new ArrayList<>();
}
