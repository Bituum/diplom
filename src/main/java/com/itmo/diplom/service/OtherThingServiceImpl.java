package com.itmo.diplom.service;

import com.itmo.diplom.DAO.OtherThingRepository;
import com.itmo.diplom.entities.OtherThingEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OtherThingServiceImpl implements OtherThingService{
    @Autowired
    private OtherThingRepository otherThingRepository;

    @Override
    public List<OtherThingEntity> getAllOtherThingEntities() {
        return otherThingRepository.findAll();
    }

    @Override
    public void saveOtherThingsEntities(OtherThingEntity otherThingEntity) {
        otherThingRepository.save(otherThingEntity);
    }

    @Override
    public OtherThingEntity getOtherThingEntity(int id) {
        OtherThingEntity otherThingEntity = null;
        Optional<OtherThingEntity> optional = otherThingRepository.findById(id);
        if(optional.isPresent()){
            otherThingEntity = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return otherThingEntity;
    }

    @Override
    public void deleteOtherThingEntity(int id) {
        otherThingRepository.deleteById(id);
    }
}
