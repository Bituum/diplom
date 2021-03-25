package com.itmo.diplom.service;

import com.itmo.diplom.entities.StorageEntity;
import com.itmo.diplom.entities.UserEntity;

import java.util.List;

public interface StorageService {
    List<StorageEntity> getAllStorageEntities();
    void saveStorageEntity(StorageEntity storage);
    StorageEntity getStorageEntity(int id);
    void deleteStorageEntity(int id);
}
