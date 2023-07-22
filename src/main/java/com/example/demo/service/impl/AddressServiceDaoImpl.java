package com.example.demo.service.impl;

import com.example.demo.service.AddressServiceDao;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.model.Address;
import com.example.demo.model.dto.AddressDto;
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
public class AddressServiceDaoImpl implements AddressServiceDao {
    @PersistenceContext
    private final EntityManager manager;
    private final AddressMapper mapper;

    @Override
    public List<AddressDto> get() {
        return mapper.toDto(manager.createQuery("select q from Address q", Address.class).getResultList());
    }

    @Override
    public AddressDto get(Long id) {
        Query query = manager.createQuery("select u from Address u where u.id = :myId", Address.class);
        query.setParameter("myId", id);
        return mapper.toDto((Address) query.getSingleResult());
    }

    @Override
    public void create(AddressDto dto) {
        manager.persist(mapper.toEntity(dto));
    }

    @Override
    public void update(Long id, AddressDto dto) {
        Address entity = mapper.toEntity(get(id));
        Address address = mapper.toEntity(dto);
        if(address.getAddress() != null){
            entity.setAddress(address.getAddress());
        }
    }

    @Override
    public void delete(Long id) {
        Address address = mapper.toEntity(get(id));
        Object managed = manager.merge(address);
        manager.remove(managed);
    }
    @Override
    public List<AddressDto> getAllById(Long id) {
        Query query = manager.createQuery("select u from Address u where u.address = :myId", Address.class);
        query.setParameter("myId", id);
        return mapper.toDto(query.getResultList());
    }
}
