package com.itmo.diplom.service;

import com.itmo.diplom.entities.ProductsEntity;
import com.itmo.diplom.entities.StorageEntity;

import java.util.List;

public interface ProductsService {
    List<ProductsEntity> getAllProductsEntities();
    void saveStorageEntity(ProductsEntity product);
    ProductsEntity getProductsEntity(int id);
    void deleteProductsEntity(int id);
}
