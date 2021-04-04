package com.itmo.diplom.service;

import com.itmo.diplom.entity.StorageEntity;

import java.util.List;

public interface StorageService {
    List<StorageEntity> getAllStorageEntities();
    void saveStorageEntity(StorageEntity storage);
    StorageEntity getStorageEntity(int id);
    void deleteStorageEntity(int id);
}
