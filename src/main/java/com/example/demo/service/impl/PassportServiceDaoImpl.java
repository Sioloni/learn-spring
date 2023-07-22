package com.example.demo.service.impl;

import com.example.demo.service.PassportServiceDao;
import com.example.demo.mapper.PassportMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.Passport;
import com.example.demo.model.dto.PassportDto;
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
public class PassportServiceDaoImpl implements PassportServiceDao {
    @PersistenceContext
    private final EntityManager manager;
    private final PassportMapper mapper;

    @Override
    public List<PassportDto> get() {
        return mapper.toDto(manager.createQuery("select u from Passport u", Passport.class).getResultList());
    }

    @Override
    public PassportDto get(Long id) {
        Query query = manager.createQuery("select u from Passport u where u.id = :myId");
        query.setParameter("myId", id);
        return mapper.toDto((Passport) query.getSingleResult());
    }

    @Override
    public void create(PassportDto dto) {
        manager.persist(mapper.toEntity(dto));
    }

    @Override
    public void update(Long id, PassportDto dto) {
        Passport entity = mapper.toEntity(get(id));
        Passport passport = mapper.toEntity(dto);
        if(passport.getNumber() != null && !entity.getNumber().isEmpty()){
            entity.setNumber(passport.getNumber());
        }
        if(passport.getEmployee().getId() != null){
            entity.setEmployee(new Employee(entity.getEmployee().getId(), entity.getEmployee().getAge(), entity.getEmployee().getName(), entity.getEmployee().getSurname(), passport.getEmployee().getPassport(), entity.getEmployee().getAddress()));
        }
        if(passport.getEmployee().getName() != null && !entity.getEmployee().getName().isEmpty()){
            entity.setEmployee(new Employee(entity.getEmployee().getId(), entity.getEmployee().getAge(), passport.getEmployee().getName(), entity.getEmployee().getSurname(), entity.getEmployee().getPassport(), entity.getEmployee().getAddress()));
        }
    }

    @Override
    public void delete(Long id) {
        Passport passport = mapper.toEntity(get(id));
        Object managed = manager.merge(passport);
        manager.remove(managed);
    }

    @Override
    public List<PassportDto> getAllById(List<Long> id) {
        Query query = manager.createQuery("select u from Passport u where u.employee = :myId", Passport.class);
        query.setParameter("myId", id);
        return mapper.toDto(query.getResultList());
    }
}
