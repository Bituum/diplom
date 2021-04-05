package com.itmo.diplom.service;

import com.itmo.diplom.entity.ProductsEntity;

import java.util.List;

public interface ProductsService {
    List<ProductsEntity> getAllProductsEntities();
    void save(ProductsEntity product);
    ProductsEntity getProductsEntity(int id);
    void deleteProductsEntity(int id);
}
