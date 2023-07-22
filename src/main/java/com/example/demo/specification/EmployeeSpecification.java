package com.example.demo.specification;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeFilter;
import com.example.demo.model.Employee_;
import com.example.demo.model.Passport_;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


@UtilityClass
public class EmployeeSpecification {


    public Specification<Employee> getIdsIn(List<Long> ids){
        return (root, query, criteriaBuilder) -> root.get(Employee_.id.getName()).in(ids);
    }

    public Specification<Employee> getAgeLessThen(Integer age){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Employee_.age.getName()), age);
    }

    public Specification<Employee> getNameLike(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Employee_.name.getName()), "%" + name + "%");
    }

    public Specification<Employee> getSurnameLike(String surname){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Employee_.surname.getName()), "%" + surname + "%");
    }
    public Specification<Employee> getNumberLike(String number){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Passport_.number.getName()), "%" + number + "%");
    }

    public Specification<Employee> getFilter(EmployeeFilter filter){
        Specification<Employee> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        if(filter.getIds() != null && filter.getIds().size() != 0){
            specification = specification.and(getIdsIn(filter.getIds()));
        }
        if(filter.getAge() != null && !filter.getAge().toString().isEmpty()){
            specification = specification.and(getAgeLessThen(filter.getAge()));
        }
        if(filter.getName() != null && !filter.getName().isEmpty()){
            specification = specification.and(getNameLike(filter.getName()));
        }
        if(filter.getSurname() != null && !filter.getSurname().isEmpty()){
            specification = specification.and(getSurnameLike(filter.getSurname()));
        }
        return specification;
    }










}
