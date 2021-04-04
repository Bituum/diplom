package com.itmo.diplom.service;

import com.itmo.diplom.entity.ProductPropertiesEntity;

import java.util.List;

public interface ProductPropertiesService {
    List<ProductPropertiesEntity> getAllProductPropertiesEntities();
    void saveOtherThingsEntities(ProductPropertiesEntity productProperties);
    ProductPropertiesEntity getProductPropertiesEntity(int id);
    void deleteProductPropertiesEntity(int id);
}
