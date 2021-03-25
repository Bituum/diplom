package com.itmo.diplom.service;

import com.itmo.diplom.DAO.StorageRepository;
import com.itmo.diplom.entities.MenuEntity;
import com.itmo.diplom.entities.StorageEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public List<StorageEntity> getAllStorageEntities() {
        return storageRepository.findAll();
    }

    @Override
    public void saveStorageEntity(StorageEntity storage) {
        storageRepository.save(storage);
    }

    @Override
    public StorageEntity getStorageEntity(int id) {
        StorageEntity tmpStorage = null;
        Optional<StorageEntity> optional = storageRepository.findById(id);
        if(optional.isPresent()){
            tmpStorage = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpStorage;
    }

    @Override
    public void deleteStorageEntity(int id) {
        storageRepository.deleteById(id);
    }
}
