package com.example.demo.service.impl;

import com.example.demo.model.EmployeeFilter;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeServiceDao;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceDaoImpl implements EmployeeServiceDao {
    @PersistenceContext
    private final EntityManager manager;
    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;


    public List<EmployeeDto> get() {
        return mapper.toDto(manager.createQuery("select q from Employee q", Employee.class).getResultList());
    }

    @Override
    public EmployeeDto get(Long id) {
        Query query = manager.createQuery("select u from Employee u where u.id = :myId", Employee.class);
        query.setParameter("myId", id);
        return mapper.toDto((Employee) query.getSingleResult());
    }

    @Override
    public void create(EmployeeDto dto) {
        manager.persist(mapper.toEntity(dto));
    }

    @Override
    public void update(Long id, EmployeeDto dto) {
        Employee entity = mapper.toEntity(get(id));
        Employee employee = mapper.toEntity(dto);
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
        manager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Employee employee = mapper.toEntity(get(id));
        Object managed = manager.merge(employee);
        manager.remove(managed);
    }

    @Override
    public Employee getEntity(Long id) {
        Query query = manager.createQuery("select u from Employee u where u.id = :myId", Employee.class);
        query.setParameter("myId", id);
        return (Employee) query.getSingleResult();
    }

    @Override
    public EmployeeDto getByIdAndName(Long id, String name) {
        Query query = manager.createQuery("select u from Employee u where u.id = :myId and u.name = :myName");
        query.setParameter("myId", id);
        query.setParameter("myName", name);
        return mapper.toDto((Employee) query.getSingleResult());
    }

    @Override
    public List<EmployeeDto> getFilter(EmployeeFilter filter) {
        return mapper.toDto(repository.findAll(EmployeeSpecification.getFilter(filter)));
    }

}
